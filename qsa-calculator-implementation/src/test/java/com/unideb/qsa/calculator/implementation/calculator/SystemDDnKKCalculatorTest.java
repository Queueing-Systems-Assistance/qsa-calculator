package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemDDnKKCalculator}.
 */
public class SystemDDnKKCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemDDnKKCalculator systemDDnKKCalculatorUnderTest = new SystemDDnKKCalculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.884956;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.44247788;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void zTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.z(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.66666666666667;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.013333334;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.44247788;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.33333333333334;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0;
        // WHEN
        double result = systemDDnKKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.Da, 1.13);
        features.put(SystemFeature.Ds, 2.26);
        features.put(SystemFeature.n, 3.0);
        features.put(SystemFeature.c, 2.0);
        features.put(SystemFeature.KFin, 4.0);
        return features;
    }
}
