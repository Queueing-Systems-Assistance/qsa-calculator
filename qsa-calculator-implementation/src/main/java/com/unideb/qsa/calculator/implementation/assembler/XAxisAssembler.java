package com.unideb.qsa.calculator.implementation.assembler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.XAxis;
import com.unideb.qsa.calculator.implementation.validator.DefaultFeatureValidator;

/**
 * Assembles the x axis for a line chart.
 */
@Component
public class XAxisAssembler {

    @Autowired
    private DefaultFeatureValidator featureValidator;

    /**
     * Assembles the x axis values.
     *
     * @param xAxis xAxis properties
     * @return List x axis values
     */
    public List<Double> assemble(Map<XAxis, Double> xAxis) {
        featureValidator.validateXAxis(xAxis);
        return DoubleStream.iterate(xAxis.get(XAxis.from), value -> value < xAxis.get(XAxis.to), nextValue -> nextValue += xAxis.get(XAxis.steps))
                           .boxed()
                           .collect(Collectors.toUnmodifiableList());
    }
}
