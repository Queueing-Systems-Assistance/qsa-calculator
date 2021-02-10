package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMG1nonPreempCalculator}.
 */
public class SystemMG1nonPreempCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMG1nonPreempCalculator systemMG1nonPreempCalculatorUnderTest = new SystemMG1nonPreempCalculator();

    @Test
    public void LambdaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.1;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.Lambda(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.151219;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.27804878;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.eSPow2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.349146;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.eSPow3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.06;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2S1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.29;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2S2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.36;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2S3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.Ro1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.Ro2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.22;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.Ro3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.62;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.71249999;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.WAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.1875;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.WAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.5;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.WAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.42378;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2W1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.17786458;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2W1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2W2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.7497829861;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2W2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.2751632;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6855208;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.EW2c1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.1599392;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.EW2c2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 20.84917153;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.EW2c3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.302314;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7124999;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.QAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.375;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.QAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.75;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.QAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.8375;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.912499;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.TAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.2875;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.TAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.7;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.TAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.575;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.237864;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2T1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.039782986111111;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2T2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 14.9591715399;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2T3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.55339646;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.91249999;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.NAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.575;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.NAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.97;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.NAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.4575;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.NAvg(features);
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
        features.put(SystemFeature.Lambda3, 1.1);
        features.put(SystemFeature.eSc3, 0.2);
        features.put(SystemFeature.eSPow2c3, 0.4);
        features.put(SystemFeature.eSPow3c3, 0.165);
        return features;
    }
}
