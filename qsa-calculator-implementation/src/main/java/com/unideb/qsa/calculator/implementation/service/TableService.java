package com.unideb.qsa.calculator.implementation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.table.TableRepresentation;
import com.unideb.qsa.calculator.implementation.resolver.SystemElementResolver;
import com.unideb.qsa.calculator.implementation.resolver.SystemOutputResolver;

/**
 * Table service.
 */
@Component
public class TableService {

    @Autowired
    private SystemElementResolver systemElementResolver;
    @Autowired
    private SystemOutputResolver systemOutputResolver;

    /**
     * Creates a table based on the input.
     *
     * @param systemId system id
     * @param features features and values from the request
     * @return a created table
     */
    public TableRepresentation createTable(String systemId, Map<SystemFeature, Double> features) {
        return new TableRepresentation.Builder()
                .withName(systemElementResolver.resolve(systemId))
                .withSystemOutputs(systemOutputResolver.resolve(systemId, features))
                .build();
    }
}
