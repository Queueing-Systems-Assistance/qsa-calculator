package com.unideb.qsa.calculator.implementation.resolver;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.resolver.i18n.MessageResolver;
import com.unideb.qsa.calculator.implementation.validator.feature.OutputFeatureValidatorFacade;

/**
 * Resolves a system feature value.
 */
@Component
public class SystemCalculatorResolver {

    private static final Logger LOG = LoggerFactory.getLogger(SystemCalculatorResolver.class);
    private static final String ERROR_CALCULATION_FAILED = "Calculation failed, method [{}] and system [{}]";
    private static final String CALCULATOR_BEAN_NAME = "%sCalculator";
    private static final List<String> EMPTY_RESULT = List.of();

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private OutputFeatureValidatorFacade outputFeatureValidatorFacade;
    @Autowired
    private MessageResolver messageResolver;

    /**
     * Resolves a system feature value by calling the corresponding calculator.
     * @param systemId used for resolving the correct calculator
     * @param outputId used for resolving the correct feature
     * @param features features and values from the request
     * @return String calculated value if it was success, {@link Double#NaN} if it wasn't
     */
    public List<String> resolve(String systemId, String outputId, Map<SystemFeature, Double> features) {
        List<String> result = validateOutputFeature(features, systemId, outputId);
        setDefaultValues(features);
        if (result.isEmpty()) {
            result = calculateResult(features, systemId, outputId);
        }
        return result;
    }

    private void setDefaultValues(Map<SystemFeature, Double> features) {
        Arrays.stream(SystemFeature.values())
              .filter(systemFeature -> !features.containsKey(systemFeature))
              .forEach(systemFeature -> features.put(systemFeature, 0.0));
    }

    private List<String> validateOutputFeature(Map<SystemFeature, Double> features, String systemId, String outputId) {
        return outputFeatureValidatorFacade.validate(features, systemId, outputId)
                                           .values()
                                           .stream()
                                           .flatMap(i18nKeys -> i18nKeys.stream().map(messageResolver::resolve))
                                           .collect(Collectors.toList());
    }

    private List<String> calculateResult(Map<SystemFeature, Double> features, String systemId, String outputId) {
        List<String> result = EMPTY_RESULT;
        try {
            Object systemService = applicationContext.getBean(String.format(CALCULATOR_BEAN_NAME, systemId));
            result = List.of(Double.toString((Double) systemService.getClass().getMethod(outputId, Map.class).invoke(systemService, features)));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.warn(ERROR_CALCULATION_FAILED, outputId, systemId);
        }
        return result;
    }
}
