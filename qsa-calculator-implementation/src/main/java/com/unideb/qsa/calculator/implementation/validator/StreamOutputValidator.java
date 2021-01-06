package com.unideb.qsa.calculator.implementation.validator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.mergeMaps;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.StreamOutput;

/**
 * Validator for {@link StreamOutput}.
 */
@Component
public class StreamOutputValidator {

    /**
     * Validate system features.
     * @param streamOutput stream input values (from, to, steps)
     * @return Validation errors if the stream output is not compliant, otherwise an empty list
     */
    public Map<String, List<String>> validate(Map<StreamOutput, String> streamOutput) {
        Map<String, List<String>> fromErrors = validateStreamOutputElement(streamOutput, StreamOutput.from, "error.positive.parameter.from");
        Map<String, List<String>> toErrors = validateStreamOutputElement(streamOutput, StreamOutput.to, "error.positive.parameter.to");
        Map<String, List<String>> stepErrors = validateSteps(streamOutput);
        return Stream.of(fromErrors, toErrors, stepErrors)
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
            result = Map.of(String.valueOf(StreamOutput.steps), List.of("error.positiveOrNegative.parameter.steps"));
        }
        return result;
    }

}
