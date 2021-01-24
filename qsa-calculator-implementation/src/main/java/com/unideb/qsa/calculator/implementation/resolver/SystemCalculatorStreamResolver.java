package com.unideb.qsa.calculator.implementation.resolver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.StreamOutput;
import com.unideb.qsa.calculator.domain.calculator.request.StreamOutputFeatureRequest;

/**
 * Resolves a streamed system feature value.
 */
@Component
public class SystemCalculatorStreamResolver {

    @Autowired
    private SystemCalculatorResolver systemCalculatorResolver;
    @Autowired
    private StreamResolver streamResolver;

    /**
     * Resolves a streamed system feature value by calling the corresponding calculator.
     * @param systemId                   used for resolving the correct calculator
     * @param outputId                   used for resolving the correct feature
     * @param streamOutputFeatureRequest request
     * @return Double calculated value if it was success, {@link Double#NaN} if it wasn't
     */
    public List<String> resolve(String systemId, String outputId, StreamOutputFeatureRequest streamOutputFeatureRequest) {
        SystemFeature streamOutputId = getStreamOutputId(streamOutputFeatureRequest);
        Map<StreamOutput, String> streamOutput = streamOutputFeatureRequest.getStreamOutput();
        Map<SystemFeature, Double> features = streamOutputFeatureRequest.getFeatureConditions();
        return DoubleStream.iterate(
                Double.parseDouble(streamOutput.get(StreamOutput.from)),
                value -> streamResolver.shouldCalculateStream(streamOutput, value),
                value -> streamResolver.calculateNextValue(streamOutput, value))
                           .boxed()
                           .map(nextValue -> features.put(streamOutputId, nextValue))
                           .map(nextValue -> features)
                           .map(updatedFeatures -> systemCalculatorResolver.resolve(systemId, outputId, updatedFeatures))
                           .flatMap(List::stream)
                           .collect(Collectors.toList());
    }

    private SystemFeature getStreamOutputId(StreamOutputFeatureRequest streamOutputFeatureRequest) {
        return SystemFeature.valueOf(streamOutputFeatureRequest.getStreamOutput().get(StreamOutput.featureId));
    }
}
