package com.unideb.qsa.calculator.implementation.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.OutputFeature;
import com.unideb.qsa.calculator.domain.calculator.request.OutputFeatureRequest;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.domain.exception.QSAInvalidOutputException;
import com.unideb.qsa.calculator.implementation.resolver.MessageResolver;
import com.unideb.qsa.calculator.implementation.resolver.SystemOutputResolver;

/**
 * Service which calculates system outputs based on the requested ids.
 */
@Component
public class OutputFeatureService {

    private static final String ERROR_NO_FEATURES_FOUND = "error.bad.request.no.feature.available.with.id";
    @Autowired
    private MessageResolver messageResolver;

    @Autowired
    private SystemOutputResolver systemOutputResolver;

    /**
     * Calculates system outputs.
     * @param systemId             system id
     * @param outputFeatureRequest the request
     * @return calculated system outputs
     */
    public List<OutputFeature> getSystemOutputs(String systemId, OutputFeatureRequest outputFeatureRequest) {
        Map<SystemFeature, Double> features = outputFeatureRequest.getFeatureConditions();
        List<String> featureIds = outputFeatureRequest.getOutputFeatureIds();
        List<OutputFeature> result = systemOutputResolver.resolve(systemId, features);
        return filterResult(featureIds, result);
    }

    /**
     * Calculates system outputs with streaming.
     * @param systemId                   system id
     * @param streamOutputFeatureRequest request
     * @return calculated system outputs
     */
    public List<OutputFeature> getSystemOutputs(String systemId, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        List<OutputFeature> result = systemOutputResolver.resolve(systemId, streamOutputFeatureRequest);
        List<String> featureIds = streamOutputFeatureRequest.getOutputFeatureIds();
        return filterResult(featureIds, result);
    }

    private List<OutputFeature> filterResult(List<String> featureIds, List<OutputFeature> result) {
        List<OutputFeature> filteredResult = result.stream()
                                                   .filter(systemOutput -> featureIds.isEmpty() || featureIds.contains(systemOutput.getId()))
                                                   .collect(Collectors.toList());
        if (filteredResult.isEmpty()) {
            throw new QSAInvalidOutputException(messageResolver.resolve(ERROR_NO_FEATURES_FOUND));
        }
        return filteredResult;
    }
}
