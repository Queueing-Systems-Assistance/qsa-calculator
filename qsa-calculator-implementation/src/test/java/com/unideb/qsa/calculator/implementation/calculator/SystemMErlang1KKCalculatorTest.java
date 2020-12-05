package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMErlang1KKCalculator}.
 */
public class SystemMErlang1KKCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMErlang1KKCalculator systemMErlang1KKCalculatorUnderTest = new SystemMErlang1KKCalculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> systemMErlang1KK = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.E0(systemMErlang1KK);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.45454545454545;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.00891188;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.1803938;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.99108811;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.70923153;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.54640409;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.25468608;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.555315973;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 74.1398363;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.nErlang, 3.0);
        features.put(SystemFeature.Lambda, 1.5);
        features.put(SystemFeature.Mu, 2.2);
        features.put(SystemFeature.K, 3.0);
        return features;
    }
}
