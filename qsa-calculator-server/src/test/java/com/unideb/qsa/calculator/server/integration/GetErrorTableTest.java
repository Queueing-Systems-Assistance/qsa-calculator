package com.unideb.qsa.calculator.server.integration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.ResponseBodyData;

import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.server.ApplicationTest;

/**
 * Integration test for {@link ValidationErrorResponse} for {@link com.unideb.qsa.calculator.domain.table.TableRepresentation}.
 */
public class GetErrorTableTest extends ApplicationTest {

    @Test(dataProvider = "data")
    public void getErrorTable(String systemId, Map<String, String> inputs) {
        // GIVEN
        // WHEN
        List<String> responses = generateRequest(systemId, inputs);
        // THEN
        getResponseValidator().validateResponseElements(responses);
        responses.forEach(this::validateErrorTables);
    }

    @DataProvider
    public Object[][] data() {
        List<Object[]> result = populateTest("errorTable/*");
        return result.toArray(new Object[result.size()][]);
    }

    @Override
    public List<Object[]> handleResource(final List<String> resourceData, final String fileName) {
        List<Map<String, String>> inputs = getDataBetweenElements("INPUT", "INPUT_END", resourceData);
        return IntStream.iterate(0, index -> index < inputs.size(), i -> i + 1)
                        .mapToObj(index -> new Object[]{fileName, inputs.get(index)})
                        .collect(Collectors.toList());
    }

    private List<String> generateRequest(String systemId, Map<String, String> features) {
        return getRequestGenerator().sendTemplateRequests("table/" + systemId, mapMapToJsonObject(features).toString())
                                    .stream()
                                    .map(ResponseBodyData::asString)
                                    .collect(Collectors.toList());
    }

    private void validateErrorTables(String json) {
        convertResponseToStream(new JSONArray(json), ValidationErrorResponse.class).forEach(getResponseValidator()::validateValidationErrorResponse);
    }
}
