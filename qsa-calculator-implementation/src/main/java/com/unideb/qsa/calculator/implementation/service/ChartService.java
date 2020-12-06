package com.unideb.qsa.calculator.implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.chart.ChartRepresentation;
import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.implementation.assembler.XAxisAssembler;
import com.unideb.qsa.calculator.implementation.resolver.SystemElementResolver;

/**
 * Chart service.
 */
@Component
public class ChartService {

    @Autowired
    private SystemElementResolver systemElementResolver;
    @Autowired
    private XAxisAssembler xAxisAssembler;
    @Autowired
    private OutputFeatureService outputFeatureService;

    /**
     * Creates a chart based on the input.
     *
     * @param systemId     system id
     * @param xAxisId      feature id which represents the xAxis
     * @param chartRequest request
     * @return the created chart
     */
    public ChartRepresentation createChart(String systemId, SystemFeature xAxisId, ChartRequest chartRequest) {
        return new ChartRepresentation.Builder()
                .withLabels(xAxisAssembler.assemble(chartRequest.getxAxis()))
                .withSystemOutputs(outputFeatureService.getSystemOutputs(systemId, xAxisId, chartRequest))
                .withSystemElement(systemElementResolver.resolve(systemId))
                .build();
    }
}
