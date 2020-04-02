package com.unideb.qsa.calculator.implementation.assembler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.XAxis;
import com.unideb.qsa.calculator.implementation.resolver.XAxisResolver;
import com.unideb.qsa.calculator.implementation.validator.DefaultFeatureValidator;

/**
 * Assembles the x axis for a line chart.
 */
@Component
public class XAxisAssembler {

    @Autowired
    private DefaultFeatureValidator featureValidator;
    @Autowired
    private XAxisResolver xAxisResolver;

    /**
     * Assembles the x axis values.
     *
     * @param xAxis xAxis properties
     * @return List x axis values
     */
    public List<Double> assemble(Map<XAxis, Double> xAxis) {
        featureValidator.validateXAxis(xAxis);
        return DoubleStream.iterate(xAxis.get(XAxis.from), value -> xAxisResolver.checkXAxisShouldCalculate(xAxis, value), nextValue -> xAxisResolver.calculateNextValue(xAxis, nextValue))
                           .boxed()
                           .collect(Collectors.toUnmodifiableList());
    }
}
