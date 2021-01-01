package com.unideb.qsa.calculator.implementation.assembler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.InputFeature;
import com.unideb.qsa.calculator.domain.calculator.OutputFeature;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.implementation.resolver.SystemCalculatorResolver;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Assembles a {@link OutputFeature}.
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
     * Assembles a {@link InputFeature} based on its id.
     * @param systemId system id
     * @param outputId feature id
     * @param features features and values from the request
     * @return Optional {@link OutputFeature} if the feature exists and the calculation was successful, {@link Optional#empty()} otherwise
     */
    public Optional<OutputFeature> assemble(String systemId, String outputId, Map<SystemFeature, Double> features) {
        List<String> values = systemCalculatorResolver.resolve(systemId, outputId, features);
        return assembleOutputFeature(outputId, values);
    }

    /**
     * Assembles a {@link InputFeature} based on its id.
     * @param systemId                   system id
     * @param outputId                   feature id
     * @param streamOutputFeatureRequest request
     * @return Optional {@link OutputFeature} if the feature exists and the calculation was successful, {@link Optional#empty()} otherwise
     */
    public Optional<OutputFeature> assemble(String systemId, String outputId, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        List<String> values = systemCalculatorResolver.resolve(systemId, outputId, streamOutputFeatureRequest);
        return assembleOutputFeature(outputId, values);
    }

    private Optional<OutputFeature> assembleOutputFeature(String outputId, List<String> values) {
        Optional<String> resolvedFeatureName = configResolver.resolve(CONFIG_NAME, qualifierAssembler.assemble(outputId));
        return resolvedFeatureName.map(name -> createSystemOutput(outputId, name, values));
    }

    private OutputFeature createSystemOutput(String outputId, String name, List<String> values) {
        return new OutputFeature.Builder()
                .withId(outputId)
                .withName(name)
                .withDescription(configResolver.resolve(CONFIG_DESCRIPTION, qualifierAssembler.assemble(outputId)).orElse(DEFAULT_EMPTY_VALUE))
                .withValue(values)
                .build();
    }
}
