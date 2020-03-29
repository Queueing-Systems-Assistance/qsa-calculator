package com.unideb.qsa.calculator.server.integration.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.unideb.qsa.calculator.domain.chart.ChartRepresentation;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.domain.system.SystemElement;
import com.unideb.qsa.calculator.domain.system.SystemInput;
import com.unideb.qsa.calculator.domain.table.TableRepresentation;

/**
 * Validates the response.
 */
public class ResponseValidator {

    private static final int TABLE_VALUE_INDEX = 0;

    /**
     * Validate response elements if they are present and sorted.
     *
     * @param list responses
     */
    public <T> void validateResponseElements(List<T> list) {
        assertFalse(list.isEmpty());
        assertTrue(list.stream().allMatch(new ArrayList<>()::add));
    }

    /**
     * Validate {@link SystemElement}.
     *
     * @param systemElement system element
     */
    public void validateSystemElement(SystemElement systemElement) {
        assertNotNull(systemElement);
        assertTrue(StringUtils.isNotBlank(systemElement.getId()));
        assertTrue(StringUtils.isNotBlank(systemElement.getName()));
        assertTrue(StringUtils.isNotBlank(systemElement.getStatus()));
    }

    /**
     * Validate {@link TableRepresentation}.
     *
     * @param tableRepresentation response
     * @param outputs             expected system values
     */
    public void validateTableRepresentation(TableRepresentation tableRepresentation, Map<String, String> outputs) {
        assertNotNull(tableRepresentation);
        validateSystemElement(tableRepresentation.getSystemElement());
        tableRepresentation.getSystemOutputs()
                           .forEach(systemOutput -> assertEquals(outputs.get(systemOutput.getId()),
                                   String.valueOf(systemOutput.getValues().get(TABLE_VALUE_INDEX))));
    }

    /**
     * Validate {@link ChartRepresentation}.
     *
     * @param chartRepresentation response
     * @param outputs             expected system values
     */
    public void validateChartRepresentation(ChartRepresentation chartRepresentation, Map<String, List<String>> outputs) {
        assertNotNull(chartRepresentation);
        validateSystemElement(chartRepresentation.getSystemElement());
        chartRepresentation.getSystemOutputs().forEach(systemOutput -> assertEquals(outputs.get(systemOutput.getId()),
                systemOutput.getValues().stream().map(String::valueOf).collect(Collectors.toList())));
    }

    /**
     * Validate {@link SystemInput}.
     *
     * @param systemInput response
     */
    public void validateSystemInput(SystemInput systemInput) {
        assertNotNull(systemInput);
        assertTrue(StringUtils.isNotBlank(systemInput.getId()));
        assertTrue(StringUtils.isNotBlank(systemInput.getName()));
        assertTrue(StringUtils.isNotBlank(systemInput.getDescription()));
    }

    /**
     * Validate {@link ValidationErrorResponse}.
     *
     * @param validationErrorResponse response
     */
    public void validateValidationErrorResponse(ValidationErrorResponse validationErrorResponse) {
        assertNotNull(validationErrorResponse);
        assertTrue(StringUtils.isNotBlank(validationErrorResponse.getErrorMessage()));
        assertNotNull(validationErrorResponse.getInputIds());
        validationErrorResponse.getInputIds().forEach(inputId -> assertTrue(StringUtils.isNotBlank(inputId)));
    }
}
