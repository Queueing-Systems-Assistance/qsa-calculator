package com.unideb.qsa.calculator.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.chart.ChartRepresentation;
import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.implementation.service.ChartService;

/**
 * Controller for charts.
 */
@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;

    /**
     * Mapping for chart.
     *
     * @return system with values for chart representation
     */
    @PostMapping("chart/{systemId}/{xAxisId}")
    @Timed(name = "controller.get.chart", absolute = true)
    @ExceptionMetered(name = "exception.controller.chart", absolute = true)
    public ChartRepresentation getChart(
            @PathVariable String systemId,
            @PathVariable SystemFeature xAxisId,
            @RequestBody ChartRequest chartRequest) {
        return chartService.createChart(systemId, xAxisId, chartRequest);
    }
}
