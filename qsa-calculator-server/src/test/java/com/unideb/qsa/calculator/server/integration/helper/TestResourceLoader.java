package com.unideb.qsa.calculator.server.integration.helper;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Loads test resources.
 */
public class TestResourceLoader {

    public static final String EMPTY_CONTENT = "";

    /**
     * Reads JSON test resource to string.
     * @param path the resource path to read from
     * @return the loaded resource
     */
    public String read(String path) {
        String result = EMPTY_CONTENT;
        try {
            result = new String(Files.readAllBytes(Path.of(path)));
        } catch (IOException e) {
            fail(String.format("Resource not found [%s]", path));
        }
        return result;
    }
}
