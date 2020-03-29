package com.unideb.qsa.calculator.server.integration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.response.ResponseBodyData;

import com.unideb.qsa.calculator.domain.chart.ChartRepresentation;
import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.server.ApplicationTest;

/**
 * Integration test for {@link ChartRequest}.
 */
public class GetChartTest extends ApplicationTest {

    @Test(dataProvider = "data")
    public void getChart(String systemId, String xAxisId, Map<String, String> xAxis, Map<String, String> inputFeatures, Map<String, List<String>> outputs) {
        // GIVEN
        // WHEN
        List<String> responses = generateRequest(systemId, xAxisId, xAxis, inputFeatures);
        // THEN
        getResponseValidator().validateResponseElements(responses);
        responses.forEach(response -> validateCharts(response, outputs));
    }

    @DataProvider
    public Object[][] data() {
        List<Object[]> result = populateTest("chart/*");
        return result.toArray(new Object[result.size()][]);
    }

    @Override
    public List<Object[]> handleResource(List<String> resourceData, String fileName) {
        List<Map<String, String>> inputs = getDataBetweenElements("INPUT", "INPUT_END", resourceData);
        List<Map<String, String>> outputs1 = getDataBetweenElements("OUTPUT_1", "OUTPUT_1_END", resourceData);
        List<Map<String, String>> outputs2 = getDataBetweenElements("OUTPUT_2", "OUTPUT_2_END", resourceData);
        List<Map<String, String>> outputs3 = getDataBetweenElements("OUTPUT_3", "OUTPUT_3_END", resourceData);
        List<Map<String, String>> xAxis = getDataBetweenElements("X_AXIS", "X_AXIS_END", resourceData);
        List<Map<String, String>> xAxisId = getDataBetweenElements("X_AXIS_ID", "X_AXIS_ID_END", resourceData);
        return IntStream.iterate(0, index -> index < Math.max(inputs.size(), xAxis.size()), i -> i + 1)
                        .mapToObj(index -> new Object[]{
                                fileName,
                                xAxisId.get(index).values().iterator().next(),
                                xAxis.get(index),
                                inputs.get(index),
                                createOutputs(outputs1.get(index), outputs2.get(index), outputs3.get(index))})
                        .collect(Collectors.toList());
    }

    private Map<String, List<String>> createOutputs(Map<String, String> outputs1, Map<String, String> outputs2,
            Map<String, String> outputs3) {
        Map<String, List<String>> result = new HashMap<>();
        outputs1.forEach(fillOutput(result));
        outputs2.forEach(fillOutput(result));
        outputs3.forEach(fillOutput(result));
        return result;
    }

    private BiConsumer<String, String> fillOutput(Map<String, List<String>> result) {
        return (key, value) -> {
            if (result.containsKey(key)) {
                result.get(key).add(value);
            } else {
                List<String> outputValue = new ArrayList<>();
                outputValue.add(value);
                result.put(key, outputValue);
            }
        };
    }

    private List<String> generateRequest(String systemId, String xAxisId, Map<String, String> xAxis, Map<String, String> inputFeatures) {
        JsonObject requestBody = createRequestBody(xAxis, inputFeatures);
        return getRequestGenerator().sendTemplateRequests("chart/" + systemId + "/" + xAxisId, requestBody.toString())
                                    .stream()
                                    .map(ResponseBodyData::asString)
                                    .collect(Collectors.toList());
    }

    private JsonObject createRequestBody(Map<String, String> xAxis, Map<String, String> inputFeatures) {
        JsonObject inputFeaturesJson = mapMapToJsonObject(inputFeatures);
        JsonObject xAxisJson = mapMapToJsonObject(xAxis);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("features", inputFeaturesJson);
        jsonObject.add("xAxis", xAxisJson);
        return jsonObject;
    }

    private void validateCharts(String json, Map<String, List<String>> outputs) {
        ChartRepresentation tableRepresentation = convertResponseToObject(new JSONObject(json), ChartRepresentation.class);
        getResponseValidator().validateChartRepresentation(tableRepresentation, outputs);
    }
}
