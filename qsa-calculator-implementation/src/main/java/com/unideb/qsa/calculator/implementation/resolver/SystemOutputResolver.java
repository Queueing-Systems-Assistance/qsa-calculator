package com.unideb.qsa.calculator.implementation.resolver;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.OutputFeature;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;
import com.unideb.qsa.calculator.domain.exception.QSAServerException;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.assembler.SystemOutputAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Resolves a {@link OutputFeature}.
 */
@Component
public class SystemOutputResolver {

    private static final String CONFIG_OUTPUTS_DEFAULT = "OUTPUTS_DEFAULT";
    private static final String ERROR_NO_OUTPUT_FOUND = "Cannot find system outputs for [%s]";

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
        return resolveOutputFeatures(systemId, outputIds -> systemOutputAssembler.assemble(systemId, outputIds, features));
    }

    /**
     * Resolves system outputs for a system.
     * @param systemId                   system id
     * @param streamOutputFeatureRequest request
     * @return List system outputs for the given system
     */
    public List<OutputFeature> resolve(String systemId, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        return resolveOutputFeatures(systemId, outputIds -> systemOutputAssembler.assemble(systemId, outputIds, streamOutputFeatureRequest));
    }

    /**
     * Resolves system outputs for a system.
     * @param systemId                   system id
     * @return List system outputs for the given system
     */
    public List<OutputFeature> resolve(String systemId) {
        return resolveOutputFeatures(systemId, outputIds -> systemOutputAssembler.assemble(outputIds));
    }

    private List<OutputFeature> resolveOutputFeatures(String systemId, Function<String[], List<OutputFeature>> supplier) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        String[] outputIds = configResolver.resolve(CONFIG_OUTPUTS_DEFAULT, qualifier, String[].class)
                                           .orElseThrow(() -> new QSAServerException(String.format(ERROR_NO_OUTPUT_FOUND, systemId)));
        return supplier.apply(outputIds);
    }
}
