package com.unideb.qsa.calculator.implementation.resolver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.StreamOutput;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.domain.error.ErrorResponse;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.service.ErrorService;
import com.unideb.qsa.calculator.implementation.validator.DefaultFeatureValidator;

/**
 * Resolves a system feature value.
 */
@Component
public class SystemCalculatorResolver {

    private static final Logger LOG = LoggerFactory.getLogger(SystemCalculatorResolver.class);
    private static final String SYSTEM_TRANSFORM_FAILED = "Calculation failed, method [{}] and system [{}]";
    private static final String CALCULATOR_BEAN_NAME = "%sCalculator";

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DefaultFeatureValidator featureValidator;
    @Autowired
    private StreamResolver streamResolver;
    @Autowired
    private ErrorService errorService;

    /**
     * Resolves a system feature value by calling the corresponding calculator.
     * @param systemId used for resolving the correct calculator
     * @param outputId used for resolving the correct feature
     * @param features features and values from the request
     * @return String calculated value if it was success, {@link Double#NaN} if it wasn't
     */
    public List<String> resolve(String systemId, String outputId, Map<SystemFeature, Double> features) {
        featureValidator.validate(features, systemId);
        List<String> result = new ArrayList<>();
        Object systemService = applicationContext.getBean(String.format(CALCULATOR_BEAN_NAME, systemId));
        try {
            List<ValidationErrorResponse> validationErrorResponses = featureValidator.validateCalculationInput(features, systemId, outputId);
            if (validationErrorResponses.isEmpty()) {
                result = List.of(Double.toString((Double) systemService.getClass().getMethod(outputId, Map.class).invoke(systemService, features)));
            } else {
                result = validationErrorResponses
                        .stream()
                        .map(validationError -> errorService.createErrorResponse(validationError.getErrorMessage()))
                        .map(ErrorResponse::getErrorMessage)
                        .collect(Collectors.toList());
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.warn(SYSTEM_TRANSFORM_FAILED, outputId, systemId);
        }
        return result;
    }

    /**
     * Resolves a system feature value by calling the corresponding calculator.
     * @param systemId                   used for resolving the correct calculator
     * @param outputId                   used for resolving the correct feature
     * @param streamOutputFeatureRequest request
     * @return Double calculated value if it was success, {@link Double#NaN} if it wasn't
     */
    public List<String> resolve(String systemId, String outputId, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        SystemFeature streamOutputId = getStreamOutputId(streamOutputFeatureRequest);
        Map<StreamOutput, String> streamOutput = streamOutputFeatureRequest.getStreamOutput();
        Map<SystemFeature, Double> features = streamOutputFeatureRequest.getFeatureConditions();
        return DoubleStream.iterate(
                Double.parseDouble(streamOutput.get(StreamOutput.from)),
                value -> streamResolver.shouldCalculateStream(streamOutput, value),
                value -> streamResolver.calculateNextValue(streamOutput, value))
                           .boxed()
                           .map(nextValue -> features.put(streamOutputId, nextValue))
                           .map(nextValue -> features)
                           .map(updatedFeatures -> resolve(systemId, outputId, updatedFeatures))
                           .flatMap(List::stream)
                           .collect(Collectors.toList());
    }

    private SystemFeature getStreamOutputId(StreamOutputFeatureRequest streamOutputFeatureRequest) {
        return SystemFeature.valueOf(streamOutputFeatureRequest.getStreamOutput().get(StreamOutput.featureId));
    }
}
