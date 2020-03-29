package com.unideb.qsa.calculator.server.integration;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.ResponseBodyData;

import com.unideb.qsa.calculator.domain.system.SystemInput;
import com.unideb.qsa.calculator.server.ApplicationTest;

/**
 * Integration test for {@link SystemInput}.
 */
public class GetInputTest extends ApplicationTest {

    @Test(dataProvider = "data")
    public void getInput(String systemId, List<String> inputIds) {
        // GIVEN
        // WHEN
        List<String> responses = generateRequest(systemId);
        // THEN
        getResponseValidator().validateResponseElements(responses);
        responses.forEach(response -> validateSystemsResponse(response, inputIds));
    }

    @DataProvider
    public Object[][] data() {
        List<Object[]> result = populateTest("input/*");
        return result.toArray(new Object[result.size()][]);
    }

    @Override
    public List<Object[]> handleResource(List<String> resourceData, String fileName) {
        List<Map<String, String>> inputs = getDataBetweenElements("ID", "ID_END", resourceData);
        return IntStream.iterate(0, index -> index < inputs.size(), i -> i + 1)
                        .mapToObj(index -> new Object[]{fileName, new ArrayList<>(inputs.get(index).values())})
                        .collect(Collectors.toList());
    }

    private List<String> generateRequest(String systemId) {
        return getRequestGenerator().sendTemplateRequests("systems/" + systemId, null)
                                    .stream()
                                    .map(ResponseBodyData::asString)
                                    .collect(Collectors.toList());
    }

    private void validateSystemsResponse(String response, List<String> inputIds) {
        List<SystemInput> systemElements = convertResponseToStream(new JSONArray(response), SystemInput.class).collect(Collectors.toList());
        assertEquals(systemElements.stream()
                                   .map(SystemInput::getId)
                                   .collect(Collectors.toList()), inputIds);
        convertResponseToStream(new JSONArray(response), SystemInput.class).forEach(getResponseValidator()::validateSystemInput);
    }

}
