package com.unideb.qsa.calculator.implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.domain.calculator.response.StreamOutputFeatureRepresentation;
import com.unideb.qsa.calculator.implementation.assembler.StreamAssembler;

/**
 * Chart service.
 */
@Component
public class OutputFeatureStreamService {

    @Autowired
    private StreamAssembler streamAssembler;
    @Autowired
    private OutputFeatureService outputFeatureService;

    /**
     * Creates a stream based on the input.
     * @param systemId                   system id
     * @param streamOutputFeatureRequest request
     * @return the created chart
     */
    public StreamOutputFeatureRepresentation createOutputFeatureStream(String systemId, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        return new StreamOutputFeatureRepresentation.Builder()
                .withStream(streamAssembler.assemble(streamOutputFeatureRequest.getStreamOutput()))
                .withOutputFeatures(outputFeatureService.getSystemOutputs(systemId, streamOutputFeatureRequest))
                .build();
    }
}
