package com.unideb.qsa.calculator.server.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;

/**
 * Logback converter with customized exception message output. Reverse the stack trace and filter out the ineffectual information.
 */
public class QsaExceptionDataConverter extends ClassicConverter {

    private static final String ROOT_CAUSE_FORMAT = "Root cause: %s: %s%n%s%n\t...%n%s";
    private static final String CAUSE_FORMAT = "Cause: %s: %s%n%s%n\t...%n";
    private static final String LEAF_CAUSE_FORMAT = "Cause: %s: %s%n%s%n\t...%n%s%n";
    private static final String STACK_STACE_BEGINNING_FORMAT = "\tat %s";
    private static final String STACK_TRACE_MIDDLE_FORMAT = "\r\n\tat ";
    private static final int START_ELEMENT = 0;
    private static final int MINIMUM_STACK_TRACE_ELEMENT = 2;
    private static final String EXCEPTION_STACK_TRACE = "[exceptionStackTrace=%n%s]%n";
    private static final String EMPTY_MESSAGE = "";
    private static final String NO_DELIMITER = "";
    private static final int MINIMUM_ELEMENTS_BETWEEN_BOUNDS = 3;

    @Override
    public String convert(ILoggingEvent loggingEvent) {
        return Optional.ofNullable(loggingEvent.getThrowableProxy())
                       .map(this::generateExceptionData)
                       .orElse(EMPTY_MESSAGE);
    }

    private String generateExceptionData(IThrowableProxy throwableProxy) {
        List<IThrowableProxy> causes = getCauses(throwableProxy);
        String formattedStackTrace = getFormattedStackTrace(causes);
        return String.format(EXCEPTION_STACK_TRACE, formattedStackTrace);
    }

    private String getFormattedStackTrace(List<IThrowableProxy> iThrowableProxies) {
        String formattedStackTraceElements = formatStackTraceElements(iThrowableProxies);
        String formattedStackTraceLeafElements = formatStackTraceLeafElements(iThrowableProxies);
        String formattedStackTraceRootElements = formatStackTraceRootElements(iThrowableProxies);
        return String.join(NO_DELIMITER, formattedStackTraceRootElements, formattedStackTraceElements, formattedStackTraceLeafElements);
    }

    private String formatStackTraceRootElements(List<IThrowableProxy> iThrowableProxies) {
        IThrowableProxy iThrowableProxy = iThrowableProxies.get(iThrowableProxies.size() - 1);
        String stackTraceStart = getStackTraceStart(iThrowableProxy.getStackTraceElementProxyArray());
        String stackTraceEnd = getStackTraceEnd(iThrowableProxies, iThrowableProxies.size() - 1);
        return String.format(ROOT_CAUSE_FORMAT, iThrowableProxy.getClassName(), iThrowableProxy.getMessage(), stackTraceStart, stackTraceEnd);
    }

    private String formatStackTraceElements(List<IThrowableProxy> iThrowableProxies) {
        String result = EMPTY_MESSAGE;
        if (hasMiddleCauses(iThrowableProxies)) {
            result = IntStream.iterate(iThrowableProxies.size() - 2, index -> index > 0, index -> index - 1)
                              .mapToObj(iThrowableProxies::get)
                              .map(iThrowableProxy -> String.format(CAUSE_FORMAT, iThrowableProxy.getClassName(), iThrowableProxy.getMessage(),
                                      getStackTraceStart(iThrowableProxy.getStackTraceElementProxyArray())))
                              .collect(Collectors.joining());
        }
        return result;
    }

    private String formatStackTraceLeafElements(List<IThrowableProxy> iThrowableProxies) {
        String result = EMPTY_MESSAGE;
        if (hasMoreThanOneCause(iThrowableProxies)) {
            IThrowableProxy iThrowableProxy = iThrowableProxies.get(START_ELEMENT);
            String stackTraceStart = getStackTraceStart(iThrowableProxy.getStackTraceElementProxyArray());
            String stackTraceEnd = getStackTraceEnd(iThrowableProxies, START_ELEMENT);
            result = String.format(LEAF_CAUSE_FORMAT, iThrowableProxy.getClassName(), iThrowableProxy.getMessage(), stackTraceStart, stackTraceEnd);
        }
        return result;
    }

    private boolean hasMiddleCauses(List<IThrowableProxy> iThrowableProxies) {
        return iThrowableProxies.size() > 2;
    }

    private boolean hasMoreThanOneCause(List<IThrowableProxy> iThrowableProxies) {
        return iThrowableProxies.size() != 1;
    }

    private String getStackTraceStart(StackTraceElementProxy[] stackTraceElementsProxy) {
        List<String> stackTraceElements = IntStream.iterate(START_ELEMENT, this::isIndexSizeWithinMinimumBound, index -> index + 1)
                                                   .mapToObj(index -> stackTraceElementsProxy[index].getStackTraceElement())
                                                   .map(StackTraceElement::toString)
                                                   .collect(Collectors.toList());
        return String.format(STACK_STACE_BEGINNING_FORMAT, String.join(STACK_TRACE_MIDDLE_FORMAT, stackTraceElements));
    }

    private String getStackTraceEnd(List<IThrowableProxy> iThrowableProxies, int currentIThrowableProxyIndex) {
        String result = EMPTY_MESSAGE;
        if (START_ELEMENT == currentIThrowableProxyIndex) {
            StackTraceElementProxy[] stackTraceElementProxies = iThrowableProxies.get(iThrowableProxies.size() - 1).getStackTraceElementProxyArray();
            List<String> stackTraceElements = IntStream.iterate(START_ELEMENT, index -> index < stackTraceElementProxies.length, index -> index + 1)
                                                       .filter(index -> isIndexWithinMaximumBound(stackTraceElementProxies, index))
                                                       .mapToObj(index -> stackTraceElementProxies[index].getStackTraceElement())
                                                       .map(StackTraceElement::toString)
                                                       .collect(Collectors.toList());
            result = String.format(STACK_STACE_BEGINNING_FORMAT, String.join(STACK_TRACE_MIDDLE_FORMAT, stackTraceElements));
        }
        return result;
    }

    private boolean isIndexSizeWithinMinimumBound(int value) {
        return value < MINIMUM_STACK_TRACE_ELEMENT;
    }

    private boolean isIndexWithinMaximumBound(StackTraceElementProxy[] stackTraceElementsProxy, int value) {
        return value > (stackTraceElementsProxy.length - MINIMUM_ELEMENTS_BETWEEN_BOUNDS);
    }

    private List<IThrowableProxy> getCauses(IThrowableProxy iThrowableProxy) {
        List<IThrowableProxy> list = new ArrayList<>(List.of(iThrowableProxy));
        IntStream.iterate(START_ELEMENT, index -> list.get(index).getCause() != null, index -> index + 1)
                 .forEach(index -> list.add(list.get(index).getCause()));
        return list;
    }

}
