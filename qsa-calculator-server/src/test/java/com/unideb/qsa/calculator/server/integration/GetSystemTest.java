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

import com.unideb.qsa.calculator.domain.system.SystemElement;
import com.unideb.qsa.calculator.server.ApplicationTest;

/**
 * Integration test for {@link SystemElement}.
 */
public class GetSystemTest extends ApplicationTest {

    @Test(dataProvider = "data")
    public void getSystems(List<String> systemIds) {
        // WHEN
        List<String> responses = getRequestGenerator()
                .sendTemplateRequests("/systems", null)
                .stream()
                .map(ResponseBodyData::asString)
                .collect(Collectors.toList());
        // THEN
        getResponseValidator().validateResponseElements(responses);
        responses.forEach(response -> validateSystemsResponse(response, systemIds));
    }

    @DataProvider
    public Object[][] data() {
        List<Object[]> result = populateTest("systems/*");
        return result.toArray(new Object[result.size()][]);
    }

    @Override
    public List<Object[]> handleResource(List<String> resourceData, String fileName) {
        List<Map<String, String>> systemIds = getDataBetweenElements("ID", "ID_END", resourceData);
        return IntStream.iterate(0, index -> index < systemIds.size(), i -> i + 1)
                        .mapToObj(index -> new Object[]{new ArrayList<>(systemIds.get(index).values())})
                        .collect(Collectors.toList());
    }

    private void validateSystemsResponse(String response, List<String> systemIds) {
        List<SystemElement> systemElements = convertResponseToStream(new JSONArray(response), SystemElement.class).collect(Collectors.toList());
        assertEquals(systemElements.stream()
                                   .map(SystemElement::getId)
                                   .collect(Collectors.toList()), systemIds);
        convertResponseToStream(new JSONArray(response), SystemElement.class).forEach(getResponseValidator()::validateSystemElement);
    }
}
