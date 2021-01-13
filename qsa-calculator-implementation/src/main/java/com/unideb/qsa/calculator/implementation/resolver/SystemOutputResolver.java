package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.OutputFeature;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.domain.exception.QSAInvalidOutputException;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.assembler.SystemOutputAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Resolves a {@link OutputFeature}.
 */
@Component
public class SystemOutputResolver {

    private static final String ERROR_NO_OUTPUT_FOUND = "error.bad.request.no.feature.available";
    private static final String CONFIG_OUTPUTS_DEFAULT = "OUTPUTS_DEFAULT";

    @Autowired
    private SystemOutputAssembler systemOutputAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Resolves system outputs for a system.
     * @param systemId system id
     * @param features features and values from the request
     * @return List system outputs for the given system
     */
    public List<OutputFeature> resolve(String systemId, Map<SystemFeature, Double> features) {
        return resolveOutputFeatures(systemId, outputId -> systemOutputAssembler.assemble(systemId, outputId, features));

    }

    /**
     * Resolves system outputs for a system.
     * @param systemId                   system id
     * @param streamOutputFeatureRequest request
     * @return List system outputs for the given system
     */
    public List<OutputFeature> resolve(String systemId, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        return resolveOutputFeatures(systemId, outputId -> systemOutputAssembler.assemble(systemId, outputId, streamOutputFeatureRequest));
    }

    private List<OutputFeature> resolveOutputFeatures(String systemId, Function<String, Optional<OutputFeature>> supplier) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        String[] outputIds = resolveOutputIds(qualifier);
        return Arrays.stream(outputIds)
                     .map(supplier)
                     .flatMap(Optional::stream)
                     .collect(Collectors.toList());
    }

    private String[] resolveOutputIds(Qualifier qualifier) {
        return configResolver.resolve(CONFIG_OUTPUTS_DEFAULT, qualifier, String[].class)
                             .orElseThrow(() -> new QSAInvalidOutputException(ERROR_NO_OUTPUT_FOUND));
    }
}
