package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMG1nonPriorCalculator}.
 */
public class SystemMG1nonPriorCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMG1nonPriorCalculator systemMG1nonPriorCalculatorUnderTest = new SystemMG1nonPriorCalculator();

    @Test
    public void LambdaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.0;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.Lambda(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.13333333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2333333333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.eSPow2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4166666666;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.eSPow3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.06;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.D2S1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.29;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.D2S2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.583333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.03472222;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.375;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.75;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.783333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.TAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.78333333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.TAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.78333333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2c1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.70833333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.ET2c1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2c2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.938333333;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.ET2c2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0947222;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.D2T1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.3247222;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.D2T2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.24805555;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.35;
        // WHEN
        double result = systemMG1nonPriorCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.Lambda1, 1.0);
        features.put(SystemFeature.eSc1, 0.2);
        features.put(SystemFeature.eSPow2c1, 0.1);
        features.put(SystemFeature.eSPow3c1, 0.05);
        features.put(SystemFeature.Lambda2, 2.0);
        features.put(SystemFeature.eSc2, 0.1);
        features.put(SystemFeature.eSPow2c2, 0.3);
        features.put(SystemFeature.eSPow3c2, 0.6);
        return features;
    }
}
