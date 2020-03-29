package com.unideb.qsa.calculator.implementation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.XAxis;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;

/**
 * Validator for X axis.
 */
@Component
public class XAxisValidator {

    /**
     * Validate system features.
     *
     * @param xAxis xAxis properties
     * @return Validation errors if the X axis is not compliant, otherwise an empty list
     */
    public List<ValidationErrorResponse> validate(Map<XAxis, Double> xAxis) {
        List<ValidationErrorResponse> result = new ArrayList<>();
        validateXAxisElement(xAxis, result, XAxis.from, "error.positive.parameter.from");
        validateXAxisElement(xAxis, result, XAxis.to, "error.positive.parameter.to");
        validateSteps(xAxis, result);
        return result;
    }

    private void validateXAxisElement(Map<XAxis, Double> xAxis, List<ValidationErrorResponse> result, XAxis xAxisFeature, String errorResponse) {
        if (!xAxis.containsKey(xAxisFeature) || xAxis.get(xAxisFeature) <= 0.0) {
            result.add(new ValidationErrorResponse.Builder()
                    .withErrorMessage(errorResponse)
                    .withInputIds(List.of(String.valueOf(xAxisFeature)))
                    .build());
        }
    }
    private void validateSteps(Map<XAxis, Double> xAxis, List<ValidationErrorResponse> result) {
        if (!xAxis.containsKey(XAxis.steps) || xAxis.get(XAxis.steps) == 0.0) {
            result.add(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.positiveOrNegative.parameter.steps")
                    .withInputIds(List.of(String.valueOf(XAxis.steps)))
                    .build());
        }
    }

}
