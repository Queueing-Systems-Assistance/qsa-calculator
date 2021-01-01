package com.unideb.qsa.calculator.implementation.assembler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.StreamOutput;
import com.unideb.qsa.calculator.implementation.resolver.StreamResolver;
import com.unideb.qsa.calculator.implementation.validator.DefaultFeatureValidator;

/**
 * Assembles the stream for streamed outputs.
 */
@Component
public class StreamAssembler {

    @Autowired
    private DefaultFeatureValidator featureValidator;
    @Autowired
    private StreamResolver streamResolver;

    /**
     * Assembles the stream values.
     * @param streamOutput stream input values (from, to, steps)
     * @return calculated stream values
     */
    public List<Double> assemble(Map<StreamOutput, String> streamOutput) {
        featureValidator.validateStreamOutput(streamOutput);
        return DoubleStream.iterate(
                Double.parseDouble(streamOutput.get(StreamOutput.from)),
                value -> streamResolver.shouldCalculateStream(streamOutput, value),
                nextValue -> streamResolver.calculateNextValue(streamOutput, nextValue))
                           .boxed()
                           .collect(Collectors.toUnmodifiableList());
    }
}
