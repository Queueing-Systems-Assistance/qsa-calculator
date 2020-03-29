package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMHyper1KKCalculator}.
 */
public class SystemMHyper1KKCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMHyper1KKCalculator systemMHyper1KKCalculatorUnderTest = new SystemMHyper1KKCalculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgWhenAllMuNotNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgWhenMu3IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.mu3, 0.0);
        double expected = 0.909090909;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgWhenMu3AndMu2IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.mu2, 0.0);
        features.put(SystemFeature.mu3, 0.0);
        double expected = 0.83333333333333;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0230857925;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0WhenMu3IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.mu3, 0.0);
        double expected = 0.0072553037;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0WhenMu2AndMu3IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.mu2, 0.0);
        features.put(SystemFeature.mu3, 0.0);
        double expected = 0.00139695289;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.97691420747;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.976914207;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.73756068894;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.6512761383;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.7375606889434;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.6743619308;
        // WHEN
        double result = systemMHyper1KKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMHyper1KK = new HashMap<>();
        systemMHyper1KK.put(SystemFeature.Alpha, 1.5);
        systemMHyper1KK.put(SystemFeature.mu1, 1.2);
        systemMHyper1KK.put(SystemFeature.mu2, 1.0);
        systemMHyper1KK.put(SystemFeature.mu3, 0.8);
        systemMHyper1KK.put(SystemFeature.p1, 0.2);
        systemMHyper1KK.put(SystemFeature.p2, 0.3);
        systemMHyper1KK.put(SystemFeature.p3, 0.5);
        systemMHyper1KK.put(SystemFeature.K, 3.0);
        return systemMHyper1KK;
    }

}
