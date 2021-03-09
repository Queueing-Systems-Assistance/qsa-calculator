package com.unideb.qsa.calculator.implementation.assembler;

import static com.unideb.qsa.calculator.implementation.assembler.SystemFeatureAssembler.FEATURE_DESCRIPTION_KEY;
import static com.unideb.qsa.calculator.implementation.assembler.SystemFeatureAssembler.FEATURE_NAME_KEY;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.InputFeature;

/**
 * Creates {@link InputFeature}.
 */
@Component
public class SystemInputAssembler {

    @Autowired
    private SystemFeatureAssembler systemFeatureAssembler;

    /**
     * Assembles a {@link InputFeature} based on its id.
     * @param inputIds             input feature ids
     * @param inputRequiredIds     features are required or not
     * @param inputTypeFractionIds features type are fraction or not
     * @return Optional {@link InputFeature} if the feature exists, {@link Optional#empty()} otherwise
     */
    public List<InputFeature> assemble(String[] inputIds, String[] inputRequiredIds, String[] inputTypeFractionIds) {
        Map<String, String> resolvedI18nKeys = systemFeatureAssembler.resolveI18nKeys(inputIds);
        return Arrays.stream(inputIds)
                     .map(inputId -> assembleInputFeature(resolvedI18nKeys, inputId, inputRequiredIds, inputTypeFractionIds))
                     .collect(Collectors.toList());
    }

    private InputFeature assembleInputFeature(Map<String, String> resolvedI18nKeys, String inputId, String[] inputRequiredIds, String[] inputTypeFractionIds) {
        return new InputFeature.Builder()
                .withId(inputId)
                .withName(systemFeatureAssembler.findI18nKey(inputId, resolvedI18nKeys, String.format(FEATURE_NAME_KEY, inputId)))
                .withDescription(systemFeatureAssembler.findI18nKey(inputId, resolvedI18nKeys, String.format(FEATURE_DESCRIPTION_KEY, inputId)))
                .withInputGroup(Arrays.asList(inputRequiredIds).contains(inputId))
                .withTypeFraction(Arrays.asList(inputTypeFractionIds).contains(inputId))
                .build();
    }
}
