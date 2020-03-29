package com.unideb.qsa.calculator.server.integration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.ResponseBodyData;

import com.unideb.qsa.calculator.domain.table.TableRepresentation;
import com.unideb.qsa.calculator.server.ApplicationTest;

/**
 * Integration test for correct value calculation.
 */
public class GetTableTest extends ApplicationTest {

    @Test(dataProvider = "data")
    public void getTable(String systemId, Map<String, String> inputs, Map<String, String> outputs) {
        // GIVEN
        // WHEN
        List<String> responses = generateRequest(systemId, inputs);
        // THEN
        getResponseValidator().validateResponseElements(responses);
        responses.forEach(result -> validateTables(result, outputs));
    }

    @DataProvider
    public Object[][] data() {
        List<Object[]> result = populateTest("table/*");
        return result.toArray(new Object[result.size()][]);
    }

    @Override
    public List<Object[]> handleResource(List<String> resourceData, String fileName) {
        List<Map<String, String>> inputs = getDataBetweenElements("INPUT", "INPUT_END", resourceData);
        List<Map<String, String>> outputs = getDataBetweenElements("OUTPUT", "OUTPUT_END", resourceData);
        return IntStream.iterate(0, index -> index < Math.max(inputs.size(), outputs.size()), i -> i + 1)
                        .mapToObj(index -> new Object[]{fileName, inputs.get(index), outputs.get(index)})
                        .collect(Collectors.toList());
    }

    private List<String> generateRequest(String systemId, Map<String, String> features) {
        return getRequestGenerator().sendTemplateRequests("table/" + systemId, mapMapToJsonObject(features).toString())
                                    .stream()
                                    .map(ResponseBodyData::asString)
                                    .collect(Collectors.toList());
    }

    private void validateTables(String json, Map<String, String> outputs) {
        TableRepresentation tableRepresentation = convertResponseToObject(new JSONObject(json), TableRepresentation.class);
        getResponseValidator().validateTableRepresentation(tableRepresentation, outputs);
    }
}
