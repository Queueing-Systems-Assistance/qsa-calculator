package com.unideb.qsa.calculator.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.unideb.qsa.calculator.server.integration.helper.RequestGenerator;
import com.unideb.qsa.calculator.server.integration.helper.ResponseValidator;

/**
 * Integration test entry point.
 */
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Ignore
public abstract class ApplicationTest extends AbstractTestNGSpringContextTests {

    private final RequestGenerator requestGenerator = new RequestGenerator();
    private final ResponseValidator responseValidator = new ResponseValidator();

    public RequestGenerator getRequestGenerator() {
        return requestGenerator;
    }

    public ResponseValidator getResponseValidator() {
        return responseValidator;
    }

    public <T> Stream<T> convertResponseToStream(JSONArray jsonArray, Class<T> convert) {
        return IntStream.iterate(0, index -> index < jsonArray.length(), index -> index + 1)
                        .boxed()
                        .map(jsonArray::getJSONObject)
                        .map(jsonObject -> convertResponseToObject(jsonObject, convert));
    }

    public <T> T convertResponseToObject(JSONObject jsonObject, Class<T> convert) {
        return new Gson().fromJson(jsonObject.toString(), convert);
    }

    public abstract List<Object[]> handleResource(List<String> resourceData, String fileName);

    /**
     * Maps map key-value pairs into a json object.
     *
     * @param keys map
     * @return mapped json object
     */
    public JsonObject mapMapToJsonObject(Map<String, String> keys) {
        JsonObject features = new JsonObject();
        keys.keySet().forEach(key -> features.addProperty(key, keys.get(key)));
        return features;
    }

    protected List<Map<String, String>> getDataBetweenElements(String startRegex, String endRegex, List<String> testData) {
        List<Map<String, String>> inputs = new ArrayList<>();
        testData.subList(getIndexOf(startRegex, testData) + 1, getIndexOf(endRegex, testData))
                .forEach(data -> {
                    List<String> elements = List.of(data.split("\\|")).stream().map(String::trim).collect(Collectors.toList());
                    IntStream.iterate(2, i -> i < elements.size(), i -> i + 1)
                             .forEach(i -> {
                                 String element = elements.get(i);
                                 Map<String, String> input = new LinkedHashMap<>();
                                 input.put(elements.get(1), element);
                                 int testIndex = i - 2;
                                 if (testIndex >= inputs.size()) {
                                     inputs.add(input);
                                 } else {
                                     inputs.get(testIndex).putAll(input);
                                 }
                             });
                });
        return inputs;
    }

    protected List<Object[]> populateTest(String resourceLocationPattern) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return List.of(resolver.getResources(resourceLocationPattern)).stream()
                       .map(resource -> {
                           String filename = Optional.ofNullable(resource.getFilename()).orElseThrow();
                           List<String> fileData = readAllData(resource);
                           return handleResource(fileData, filename.substring(0, filename.indexOf('.')));
                       })
                       .flatMap(Collection::stream)
                       .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readAllData(final Resource resource) {
        try {
            return Files.readAllLines(Paths.get(resource.getURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getIndexOf(String element, List<String> testData) {
        for (int i = 0; i < testData.size(); i++) {
            String data = testData.get(i);
            if (List.of(data.split("\\|")).stream().map(String::trim).collect(Collectors.toList()).contains(element)) {
                return i;
            }
        }
        throw new RuntimeException(String.format("No element found with [%s]", element));
    }
}
