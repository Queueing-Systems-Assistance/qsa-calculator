package com.unideb.qsa.calculator.implementation.assembler;

import static com.unideb.qsa.calculator.implementation.assembler.SystemFeatureAssembler.FEATURE_DESCRIPTION_KEY;
import static com.unideb.qsa.calculator.implementation.assembler.SystemFeatureAssembler.FEATURE_NAME_KEY;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.InputFeature;
import com.unideb.qsa.calculator.domain.calculator.OutputFeature;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.implementation.resolver.SystemCalculatorResolver;
import com.unideb.qsa.calculator.implementation.resolver.SystemCalculatorStreamResolver;

/**
 * Assembles a {@link OutputFeature}.
 */
@Component
public class SystemOutputAssembler {

    @Autowired
    private SystemFeatureAssembler systemFeatureAssembler;
    @Autowired
    private SystemCalculatorResolver systemCalculatorResolver;
    @Autowired
    private SystemCalculatorStreamResolver systemCalculatorStreamResolver;

    /**
     * Assembles a {@link InputFeature} based on its id.
     * @param systemId  system id
     * @param outputIds output feature ids of the system (based on the systemId)
     * @param features  features and values from the request
     * @return A list of assembled output features
     */
    public List<OutputFeature> assemble(String systemId, String[] outputIds, Map<SystemFeature, Double> features) {
        Map<String, String> resolvedI18nKeys = systemFeatureAssembler.resolveI18nKeys(outputIds);
        return Arrays.stream(outputIds)
                     .map(outputId -> assembleOutputFeature(resolvedI18nKeys, outputId, systemCalculatorResolver.resolve(systemId, outputId, features)))
                     .collect(Collectors.toList());
    }

    /**
     * Assembles a {@link InputFeature} based on its id.
     * @param systemId                   system id
     * @param outputIds                  output feature ids of the system (based on the systemId)
     * @param streamOutputFeatureRequest features and values from the request
     * @return A list of assembled output features
     */
    public List<OutputFeature> assemble(String systemId, String[] outputIds, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        Map<String, String> resolvedI18nKeys = systemFeatureAssembler.resolveI18nKeys(outputIds);
        return Arrays.stream(outputIds)
                     .map(outputId -> assembleOutputFeature(resolvedI18nKeys, outputId,
                             systemCalculatorStreamResolver.resolve(systemId, outputId, streamOutputFeatureRequest)))
                     .collect(Collectors.toList());
    }

    private OutputFeature assembleOutputFeature(Map<String, String> resolvedI18nKeys, String outputId, List<String> values) {
        return new OutputFeature.Builder()
                .withId(outputId)
                .withName(systemFeatureAssembler.findI18nKey(outputId, resolvedI18nKeys, String.format(FEATURE_NAME_KEY, outputId)))
                .withDescription(systemFeatureAssembler.findI18nKey(outputId, resolvedI18nKeys, String.format(FEATURE_DESCRIPTION_KEY, outputId)))
                .withValue(values)
                .build();
    }
}
