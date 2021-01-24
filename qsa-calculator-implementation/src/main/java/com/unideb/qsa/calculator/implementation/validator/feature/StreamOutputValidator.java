package com.unideb.qsa.calculator.implementation.validator.feature;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.mergeMaps;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.StreamOutput;
import com.unideb.qsa.calculator.domain.exception.QSAValidationException;
import com.unideb.qsa.calculator.implementation.resolver.MessageResolver;

/**
 * Validator for {@link StreamOutput}.
 */
@Component
public class StreamOutputValidator {

    private static final String ERROR_MESSAGE_KEY_FROM = "error.validation.feature.from.should.be.positive";
    private static final String ERROR_MESSAGE_KEY_TO = "error.validation.feature.to.should.be.positive";
    private static final String ERROR_MESSAGE_KEY_STEPS = "error.validation.feature.steps.should.not.be.zero";
    private static final String ERROR_MESSAGE_KEY_VALIDATION = "error.validation";

    @Autowired
    private MessageResolver messageResolver;
    /**
     * Validate system features.
     * @param streamOutput stream input values (from, to, steps)
     * @return Validation errors if the stream output is not compliant, otherwise an empty list
     */
    public Map<String, List<String>> validate(Map<StreamOutput, String> streamOutput) {
        Map<String, List<String>> validationErrors = getValidationErrors(streamOutput);
        if (!validationErrors.isEmpty()) {
            validationErrors.keySet().forEach(key -> validationErrors.put(key, messageResolver.resolve(validationErrors.get(key))));
            throw new QSAValidationException(messageResolver.resolve(ERROR_MESSAGE_KEY_VALIDATION), validationErrors);
        }
        return validationErrors;
    }

    private Map<String, List<String>> getValidationErrors(Map<StreamOutput, String> streamOutput) {
        Map<String, List<String>> fromErrors = validateStreamOutputElement(streamOutput, StreamOutput.from, ERROR_MESSAGE_KEY_FROM);
        Map<String, List<String>> toErrors = validateStreamOutputElement(streamOutput, StreamOutput.to, ERROR_MESSAGE_KEY_TO);
        Map<String, List<String>> stepsErrors = validateSteps(streamOutput);
        return mergeValidationErrors(Stream.of(fromErrors, toErrors, stepsErrors));
    }

    private Map<String, List<String>> mergeValidationErrors(Stream<Map<String, List<String>>> validationErrors) {
        return validationErrors
                .flatMap(map -> map.entrySet().stream())
                .collect(mergeMaps());
    }

    private Map<String, List<String>> validateStreamOutputElement(Map<StreamOutput, String> streamOutput, StreamOutput streamOutputFeature, String i18nKey) {
        Map<String, List<String>> result = Map.of();
        if (!streamOutput.containsKey(streamOutputFeature) || Double.parseDouble(streamOutput.get(streamOutputFeature)) <= 0.0) {
            result = Map.of(String.valueOf(streamOutputFeature), List.of(i18nKey));
        }
        return result;
    }

    private Map<String, List<String>> validateSteps(Map<StreamOutput, String> streamOutput) {
        Map<String, List<String>> result = Map.of();
        if (!streamOutput.containsKey(StreamOutput.steps) || Double.parseDouble(streamOutput.get(StreamOutput.steps)) == 0.0) {
            result = Map.of(String.valueOf(StreamOutput.steps), List.of(ERROR_MESSAGE_KEY_STEPS));
        }
        return result;
    }

}
