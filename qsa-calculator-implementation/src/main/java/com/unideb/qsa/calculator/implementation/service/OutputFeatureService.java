package com.unideb.qsa.calculator.implementation.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.domain.system.SystemOutput;
import com.unideb.qsa.calculator.implementation.resolver.SystemOutputResolver;

/**
 * Service which calculates system outputs based on the requested ids.
 */
@Component
public class OutputFeatureService {

    @Autowired
    private SystemOutputResolver systemOutputResolver;

    /**
     * Calculates system outputs.
     *
     * @param systemId   system id
     * @param features   features and values from the request
     * @param featureIds features ids from the request
     * @return calculated system outputs
     */
    public List<SystemOutput> getSystemOutputs(String systemId, Map<SystemFeature, Double> features, List<String> featureIds) {
        List<SystemOutput> result = systemOutputResolver.resolve(systemId, features);
        if (!featureIds.isEmpty()) {
            result = filterResult(featureIds, result);
        }
        return result;
    }
    /**
     * Calculates system outputs with streaming.
     *
     * @param systemId   system id
     * @param xAxisId      feature id which represents the xAxis
     * @param chartRequest request
     * @return calculated system outputs
     */
    public List<SystemOutput> getSystemOutputs(String systemId, SystemFeature xAxisId, ChartRequest chartRequest) {
        List<SystemOutput> result = systemOutputResolver.resolve(systemId, xAxisId, chartRequest);
        List<String> featureIds = chartRequest.getOutputFeatureIds();
        if (!featureIds.isEmpty()) {
            result = filterResult(featureIds, result);
        }
        return result;
    }

    private List<SystemOutput> filterResult(List<String> featureIds, List<SystemOutput> result) {
        return result.stream()
                     .filter(systemOutput -> featureIds.contains(systemOutput.getId()))
                     .collect(Collectors.toList());
    }

}
