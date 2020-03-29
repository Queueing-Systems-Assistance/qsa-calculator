package com.unideb.qsa.calculator.implementation.resolver;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.XAxis;
import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.implementation.validator.DefaultFeatureValidator;

/**
 * Resolves a system feature value.
 */
@Component
public class SystemCalculatorResolver {

    private static final Logger LOG = LoggerFactory.getLogger(SystemCalculatorResolver.class);
    private static final String SYSTEM_TRANSFORM_FAILED = "Calculation failed, method [{}] and system [{}]";
    private static final String CALCULATOR_BEAN_NAME = "%sCalculator";

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DefaultFeatureValidator featureValidator;

    /**
     * Resolves a system feature value by calling the corresponding calculator.
     *
     * @param systemId used for resolving the correct calculator
     * @param outputId used for resolving the correct feature
     * @param features features and values from the request
     * @return Double calculated value if it was success, {@link Double#NaN} if it wasn't
     */
    public Double resolve(String systemId, String outputId, Map<SystemFeature, Double> features) {
        featureValidator.validate(features, systemId);
        Double result = Double.NaN;
        Object systemService = applicationContext.getBean(String.format(CALCULATOR_BEAN_NAME, systemId));
        try {
            result = (Double) systemService.getClass().getMethod(outputId, Map.class).invoke(systemService, features);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.warn(SYSTEM_TRANSFORM_FAILED, outputId, systemId);
        }
        return result;
    }

    /**
     * Resolves a system feature value by calling the corresponding calculator.
     *
     * @param systemId     used for resolving the correct calculator
     * @param outputId     used for resolving the correct feature
     * @param xAxisId      feature id which represents the xAxis
     * @param chartRequest request
     * @return Double calculated value if it was success, {@link Double#NaN} if it wasn't
     */
    public List<Double> resolve(String systemId, String outputId, SystemFeature xAxisId, ChartRequest chartRequest) {
        Map<XAxis, Double> xAxis = chartRequest.getxAxis();
        Map<SystemFeature, Double> features = chartRequest.getFeatures();
        return DoubleStream.iterate(
                xAxis.get(XAxis.from),
                value -> checkXAxisShouldCalculate(xAxis, value),
                value -> calculateNextValue(xAxis, value))
                           .boxed()
                           .map(nextValue -> features.put(xAxisId, nextValue))
                           .map(nextValue -> features)
                           .map(updatedFeatures -> resolve(systemId, outputId, updatedFeatures))
                           .collect(Collectors.toList());
    }

    private boolean checkXAxisShouldCalculate(Map<XAxis, Double> xAxis, double currentValue) {
        boolean shouldCalculate;
        if (xAxis.get(XAxis.from) > xAxis.get(XAxis.to)) {
            shouldCalculate = currentValue >= xAxis.get(XAxis.to);
        } else {
            shouldCalculate = currentValue <= xAxis.get(XAxis.to);
        }
        return shouldCalculate;
    }

    private double calculateNextValue(Map<XAxis, Double> xAxis, double currentValue) {
        double steps;
        if (xAxis.get(XAxis.from) > xAxis.get(XAxis.to)) {
            steps = Math.abs(xAxis.get(XAxis.steps)) * -1;
        } else {
            steps = Math.abs(xAxis.get(XAxis.steps));
        }
        return currentValue + steps;
    }

}
