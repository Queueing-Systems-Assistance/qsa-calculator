package com.unideb.qsa.calculator.server.integration.helper;

/**
 * Utility class for creating json objects from request and response example files.
 */
public class JsonHandler {

    private static final String STUB_FILES_LOCATION = "src/test/resources/stubfiles/";
    private static final String RESPONSE_FILE_LOCATION = "response";
    private static final String REQUEST_FILE_LOCATION = "request";
    private static final String PATH_SEPARATOR = "/";
    private static final TestResourceLoader RESOURCE_LOADER = new TestResourceLoader();

    /**
     * Builds expected json body.
     * @param systemId systemId.
     * @param fileName name of the file containing the response body.
     * @return parsed response.
     */
    public String buildResponseFrom(String systemId, String fileName) {
        return RESOURCE_LOADER.read(buildPath(RESPONSE_FILE_LOCATION, systemId, fileName));
    }

    /**
     * Builds request body json.
     * @param systemId systemId.
     * @param fileName name of the file containing the request body.
     * @return parsed request.
     */
    public String buildRequestFrom(String systemId, String fileName) {
        String path = buildPath(REQUEST_FILE_LOCATION, systemId, fileName);
        return RESOURCE_LOADER.read(path);
    }

    private String buildPath(String fileLocation, String systemId, String fileName) {
        return STUB_FILES_LOCATION + systemId + PATH_SEPARATOR + fileLocation + PATH_SEPARATOR + fileName;
    }
}
