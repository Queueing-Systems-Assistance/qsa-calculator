package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.domain.system.SystemInput;
import com.unideb.qsa.calculator.domain.system.SystemOutput;
import com.unideb.qsa.calculator.implementation.resolver.SystemCalculatorResolver;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Assembles a {@link SystemOutput}.
 */
@Component
public class SystemOutputAssembler {

    private static final String DEFAULT_EMPTY_VALUE = "";
    private static final String CONFIG_NAME = "NAME";
    private static final String CONFIG_DESCRIPTION = "DESCRIPTION";

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;
    @Autowired
    private SystemCalculatorResolver systemCalculatorResolver;


    /**
     * Assembles a {@link SystemInput} based on its id.
     *
     * @param systemId system id
     * @param outputId feature id
     * @param features features and values from the request
     * @return Optional {@link SystemOutput} if the feature exists and the calculation was successful, {@link Optional#empty()} otherwise
     */
    public Optional<SystemOutput> assemble(String systemId, String outputId, Map<SystemFeature, Double> features) {
        Optional<String> optionalName = configResolver.resolve(CONFIG_NAME, qualifierAssembler.assemble(outputId));
        Optional<SystemOutput> result = Optional.empty();
        if (optionalName.isPresent()) {
            List<String> values = systemCalculatorResolver.resolve(systemId, outputId, features);
            result = Optional.of(createSystemOutput(outputId, optionalName.get(), values));
        }
        return result;
    }

    /**
     * Assembles a {@link SystemInput} based on its id.
     *
     * @param systemId system id
     * @param outputId feature id
     * @param xAxisId  feature id which represents the xAxis
     * @param chartRequest request
     * @return Optional {@link SystemOutput} if the feature exists and the calculation was successful, {@link Optional#empty()} otherwise
     */
    public Optional<SystemOutput> assemble(String systemId, String outputId, SystemFeature xAxisId, ChartRequest chartRequest) {
        Optional<String> optionalName = configResolver.resolve(CONFIG_NAME, qualifierAssembler.assemble(outputId));
        Optional<SystemOutput> result = Optional.empty();
        if (optionalName.isPresent()) {
            List<String> values = systemCalculatorResolver.resolve(systemId, outputId, xAxisId, chartRequest);
            result = Optional.of(createSystemOutput(outputId, optionalName.get(), values));
        }
        return result;
    }

    private SystemOutput createSystemOutput(String outputId, String name, List<String> values) {
        return new SystemOutput.Builder()
                .withId(outputId)
                .withName(name)
                .withDescription(configResolver.resolve(CONFIG_DESCRIPTION, qualifierAssembler.assemble(outputId)).orElse(DEFAULT_EMPTY_VALUE))
                .withValue(values)
                .build();
    }
}
