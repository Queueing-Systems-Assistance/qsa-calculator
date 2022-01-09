package com.unideb.qsa.calculator.implementation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.SystemElement;
import com.unideb.qsa.calculator.domain.exception.QSAInvalidSystemException;
import com.unideb.qsa.calculator.implementation.resolver.SystemElementResolver;
import com.unideb.qsa.calculator.implementation.resolver.i18n.MessageResolver;

/**
 * Creates system element based on system ids.
 */
@Component
public class SystemElementService {

    private static final String ERROR_NO_SYSTEMS = "error.bad.request.no.systems.available.with.id";

    @Autowired
    private SystemElementResolver systemElementResolver;
    @Autowired
    private MessageResolver messageResolver;

    /**
     * Resolves the system elements based on the given system ids. If no ID was given, then all the available system elements are returned.
     * @param systemIds system ids
     * @return system elements
     */
    public List<SystemElement> getSystemElements(List<String> systemIds) {
        List<SystemElement> result = systemElementResolver.resolve();
        if (!systemIds.isEmpty()) {
            result = filterResult(systemIds, result);
        }
        return result;
    }

    private List<SystemElement> filterResult(List<String> systemIds, List<SystemElement> result) {
        List<SystemElement> filteredResult = result.stream()
                                                   .filter(systemElement -> systemIds.contains(systemElement.getId()))
                                                   .collect(Collectors.toList());
        if (filteredResult.isEmpty()) {
            throw new QSAInvalidSystemException(messageResolver.resolve(ERROR_NO_SYSTEMS));
        }
        return filteredResult;
    }
}
