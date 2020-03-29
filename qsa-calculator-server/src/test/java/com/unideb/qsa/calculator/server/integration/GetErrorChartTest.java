package com.unideb.qsa.calculator.server.integration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.response.ResponseBodyData;

import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.server.ApplicationTest;

/**
 * Integration test for {@link ValidationErrorResponse} for {@link ChartRequest}.
 */
public class GetErrorChartTest extends ApplicationTest {

    @Test(dataProvider = "data")
    public void getTable(String systemId, String xAxisId, Map<String, String> xAxis, Map<String, String> inputFeatures) {
        // GIVEN
        // WHEN
        List<String> responses = generateRequest(systemId, xAxisId, xAxis, inputFeatures);
        // THEN
        getResponseValidator().validateResponseElements(responses);
        responses.forEach(this::validateErrorChart);
    }

    @DataProvider
    public Object[][] data() {
        List<Object[]> result = populateTest("chartError/*");
        return result.toArray(new Object[result.size()][]);
    }

    @Override
    public List<Object[]> handleResource(List<String> resourceData, String fileName) {
        List<Map<String, String>> inputs = getDataBetweenElements("INPUT", "INPUT_END", resourceData);
        List<Map<String, String>> xAxis = getDataBetweenElements("X_AXIS", "X_AXIS_END", resourceData);
        List<Map<String, String>> xAxisId = getDataBetweenElements("X_AXIS_ID", "X_AXIS_ID_END", resourceData);
        return IntStream.iterate(0, index -> index < Math.max(inputs.size(), xAxis.size()), i -> i + 1)
                        .mapToObj(index -> new Object[]{fileName, xAxisId.get(index).values().iterator().next(), xAxis.get(index), inputs.get(index)})
                        .collect(Collectors.toList());
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

    private void validateErrorChart(String json) {
        convertResponseToStream(new JSONArray(json), ValidationErrorResponse.class).forEach(getResponseValidator()::validateValidationErrorResponse);
    }
}
