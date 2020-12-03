package com.unideb.qsa.calculator.implementation.calculator;

import com.unideb.qsa.calculator.domain.SystemFeature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for {@link SystemMHypo1KK2Calculator}.
 */
public class SystemMHypo1KK2CalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMHypo1KK2Calculator systemMHypo1KK2CalculatorUnderTest = new SystemMHypo1KK2Calculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9090909;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0034614325;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.99653856;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0961924;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.07007912;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.269205;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.160988212;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.2726664;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    public Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMHypo1KK = new HashMap<>();
        systemMHypo1KK.put(SystemFeature.Lambda, 1.5);
        systemMHypo1KK.put(SystemFeature.Mu1, 1.2);
        systemMHypo1KK.put(SystemFeature.Mu2, 1.0);
        systemMHypo1KK.put(SystemFeature.K, 3.0);
        return systemMHypo1KK;
    }
}
