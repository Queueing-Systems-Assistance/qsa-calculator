package com.unideb.qsa.calculator.server.integration.config;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

import java.util.List;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.unideb.qsa.calculator.domain.calculator.OutputFeature;
import com.unideb.qsa.calculator.server.integration.helper.HeaderBuilder;
import com.unideb.qsa.calculator.server.integration.helper.JsonHandler;

/**
 * Cucumber test glues.
 */
public class CalculatorStepDefinitions extends SpringIntegrationTest {

    private static final String RESPONSE_STATUS_CODE_DOES_NOT_MATCH = "Response status code does not match with actual status code.";
    private static final TypeReference<List<OutputFeature>> LIST_OUTPUT_FEATURE = new TypeReference<>() {};
    private static final JsonHandler JSON_HANDLER = new JsonHandler();
    private static final HeaderBuilder HEADER_BUILDER = new HeaderBuilder();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ResponseEntity<String> httpResponse;
    private String system;
    private String fileName;

    //TODO: Create test cases for streams and error handling
    @Given("^I have a request for (.*) with a request JSON: (.*)$")
    public void iHaveARequest(String system, String fileName) {
        this.system = system;
        this.fileName = fileName;
    }

    @When("^I send the request to the (.*) endpoint$")
    public void iSendTheRequest(String endpoint) {
        String requestBody = JSON_HANDLER.buildRequestFrom(system, fileName);
        HttpHeaders headers = HEADER_BUILDER.setupHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate();
        httpResponse = restTemplate.exchange("http://localhost:8080" + endpoint, HttpMethod.POST, new HttpEntity<>(requestBody, headers), String.class);
    }

    @Then("^I should get back expected status as (.*)$")
    public void iShouldGetBackExpectedStatus(int status) {
        assertEquals(httpResponse.getStatusCode().value(), status, RESPONSE_STATUS_CODE_DOES_NOT_MATCH);
    }

    @Then("^I should get back the expected output features")
    public void iShouldGetBackExpectedOutputs() throws JsonProcessingException {
        List<OutputFeature> expected = OBJECT_MAPPER.readValue(JSON_HANDLER.buildResponseFrom(system, fileName), LIST_OUTPUT_FEATURE);
        List<OutputFeature> actual = OBJECT_MAPPER.readValue(httpResponse.getBody(), LIST_OUTPUT_FEATURE);
        expected.forEach(expectedOutputFeature ->
                actual.stream()
                      .filter(actualOutputFeature -> actualOutputFeature.getId().equals(expectedOutputFeature.getId()))
                      .findFirst()
                      .ifPresentOrElse(actualOutputFeature -> assertEquals(actualOutputFeature.getValues(), expectedOutputFeature.getValues(),
                              String.format("[%s] value was not [%s]", expectedOutputFeature.getId(), expectedOutputFeature.getValues())),
                              () -> fail(String.format("[%s] is not present in the response", expectedOutputFeature.getId()))));
    }

}
