package com.unideb.qsa.calculator.implementation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.StreamOutput;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;

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
    public List<ValidationErrorResponse> validate(Map<StreamOutput, String> streamOutput) {
        List<ValidationErrorResponse> result = new ArrayList<>();
        validateStreamOutputElement(streamOutput, result, StreamOutput.from, "error.positive.parameter.from");
        validateStreamOutputElement(streamOutput, result, StreamOutput.to, "error.positive.parameter.to");
        validateSteps(streamOutput, result);
        return result;
    }

    private void validateStreamOutputElement(Map<StreamOutput, String> streamOutput, List<ValidationErrorResponse> result, StreamOutput streamOutputFeature,
            String errorResponse) {
        if (!streamOutput.containsKey(streamOutputFeature) || Double.parseDouble(streamOutput.get(streamOutputFeature)) <= 0.0) {
            result.add(new ValidationErrorResponse.Builder()
                    .withErrorMessage(errorResponse)
                    .withInputIds(List.of(String.valueOf(streamOutputFeature)))
                    .build());
        }
    }

    private void validateSteps(Map<StreamOutput, String> streamOutput, List<ValidationErrorResponse> result) {
        if (!streamOutput.containsKey(StreamOutput.steps) || Double.parseDouble(streamOutput.get(StreamOutput.steps)) == 0.0) {
            result.add(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.positiveOrNegative.parameter.steps")
                    .withInputIds(List.of(String.valueOf(StreamOutput.steps)))
                    .build());
        }
    }

}
