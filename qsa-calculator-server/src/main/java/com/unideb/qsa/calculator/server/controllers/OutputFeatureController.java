package com.unideb.qsa.calculator.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unideb.qsa.calculator.domain.calculator.OutputFeature;
import com.unideb.qsa.calculator.domain.calculator.request.OutputFeatureRequest;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.domain.calculator.response.StreamOutputFeatureRepresentation;
import com.unideb.qsa.calculator.implementation.service.OutputFeatureService;
import com.unideb.qsa.calculator.implementation.service.OutputFeatureStreamService;

/**
 * Controller for system output features.
 */
@RestController
public class OutputFeatureController {

    @Autowired
    private OutputFeatureService outputFeatureService;
    @Autowired
    private OutputFeatureStreamService outputFeatureStreamService;

    /**
     * Resolves system outputs based on the requested ids.
     * @return the calculated system outputs
     */
    @PostMapping("system/{systemId}/outputs")
    public List<OutputFeature> getOutputs(
            @PathVariable String systemId,
            @RequestBody OutputFeatureRequest outputFeatureRequest) {
        return outputFeatureService.getSystemOutputs(systemId, outputFeatureRequest);
    }

    /**
     * Resolves streamed system outputs based on the requested ids.
     * @return the calculated stream system outputs
     */
    @PostMapping("system/{systemId}/outputs/stream")
    public StreamOutputFeatureRepresentation getOutputsStream(
            @PathVariable String systemId,
            @RequestBody StreamOutputFeatureRequest streamOutputFeatureRequest) {
        return outputFeatureStreamService.createOutputFeatureStream(systemId, streamOutputFeatureRequest);
    }
}
