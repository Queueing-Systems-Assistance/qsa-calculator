package com.unideb.qsa.calculator.server.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.table.TableRepresentation;
import com.unideb.qsa.calculator.implementation.service.TableService;

/**
 * Controller for tables.
 */
@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    /**
     * Mapping for a system table.
     *
     * @return the calculated system features
     */
    @PostMapping("table/{systemId}")
    @Timed(name = "controller.get.table", absolute = true)
    @ExceptionMetered(name = "exception.controller.table", absolute = true)
    public TableRepresentation getTable(
            @PathVariable String systemId,
            @RequestBody Map<SystemFeature, Double> features) {
        return tableService.createTable(systemId, features);
    }

}
