package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMHypo1KKCalculator}.
 */
public class SystemMHypo1KKCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMHypo1KKCalculator systemMHypo1KKCalculatorUnderTest = new SystemMHypo1KKCalculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgWhenAllMuIsNotNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgWhenMu3IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.Mu3, 0.0);
        double expected = 0.909090909;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgWhenMu3AndMu2IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.Mu2, 0.0);
        features.put(SystemFeature.Mu3, 0.0);
        double expected = 0.83333333333333;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.167337045E-4;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0WhenMu3IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.Mu3, 0.0);
        double expected = 0.00346143256;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0WhenMu2And3IsNullTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.Mu2, 0.0);
        features.put(SystemFeature.Mu3, 0.0);
        double expected = 0.03869407496977;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9997832662954538;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.999783266;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.66731700873135;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.6665221775303025;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.66731700873135;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.6667389112;
        // WHEN
        double result = systemMHypo1KKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    public Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMHypo1KK = new HashMap<>();
        systemMHypo1KK.put(SystemFeature.Lambda, 1.5);
        systemMHypo1KK.put(SystemFeature.Mu1, 1.2);
        systemMHypo1KK.put(SystemFeature.Mu2, 1.0);
        systemMHypo1KK.put(SystemFeature.Mu3, 0.8);
        systemMHypo1KK.put(SystemFeature.K, 3.0);
        return systemMHypo1KK;
    }
}
