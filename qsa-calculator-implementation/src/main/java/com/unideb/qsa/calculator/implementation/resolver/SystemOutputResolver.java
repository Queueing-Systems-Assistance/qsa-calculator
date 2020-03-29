package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.chart.ChartRequest;
import com.unideb.qsa.calculator.domain.exception.QSAInternalException;
import com.unideb.qsa.calculator.domain.system.SystemOutput;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.assembler.SystemOutputAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Resolves a {@link SystemOutput}.
 */
@Component
public class SystemOutputResolver {

    private static final String DEFAULT_EMPTY_VALUE = "";
    private static final String CONFIG_OUTPUTS_DEFAULT = "OUTPUTS_DEFAULT";
    private static final String ERROR_NO_FEATURE_ID = "No system feature found with id [%s]";

    @Autowired
    private SystemOutputAssembler systemOutputAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Resolves system outputs for a system.
     *
     * @param systemId system id
     * @param features features and values from the request
     * @return List system outputs for the given system
     */
    public List<SystemOutput> resolve(String systemId, Map<SystemFeature, Double> features) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        String[] outputIds = configResolver.resolve(CONFIG_OUTPUTS_DEFAULT, qualifier).orElse(DEFAULT_EMPTY_VALUE).split(",");
        return Arrays.stream(outputIds)
                     .map(outputId -> systemOutputAssembler.assemble(systemId, outputId, features).orElseThrow(getExceptionSupplier(outputId)))
                     .collect(Collectors.toList());
    }

    /**
     * Resolves system outputs for a system.
     *
     * @param systemId     system id
     * @param xAxisId      feature id which represents the xAxis
     * @param chartRequest request
     * @return List system outputs for the given system
     */
    public List<SystemOutput> resolve(String systemId, SystemFeature xAxisId, ChartRequest chartRequest) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        String[] outputIds = configResolver.resolve(CONFIG_OUTPUTS_DEFAULT, qualifier).orElse(DEFAULT_EMPTY_VALUE).split(",");
        return Arrays.stream(outputIds)
                     .map(outputId -> systemOutputAssembler.assemble(systemId, outputId, xAxisId, chartRequest).orElseThrow(getExceptionSupplier(outputId)))
                     .collect(Collectors.toList());
    }

    private Supplier<RuntimeException> getExceptionSupplier(String inputId) {
        return () -> new QSAInternalException(String.format(ERROR_NO_FEATURE_ID, inputId));
    }
}
