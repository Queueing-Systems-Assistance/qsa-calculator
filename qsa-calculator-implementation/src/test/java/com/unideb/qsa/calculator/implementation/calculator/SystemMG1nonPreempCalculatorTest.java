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
        double expected = 3.0;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.Lambda(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.13333333;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2333333333;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.eSPow2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4166666666;
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
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4374999;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.WAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.72916666;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.WAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.631944444;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2W1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.82161458;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2W1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2W2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.7306857;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2W2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.4465663;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.013020833;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.EW2c1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.2623697;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.EW2c2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.84592013;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4374999999;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.QAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.458333333;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.QAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.895833333;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6375;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.TAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.829166666;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.TAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7652777;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8816145833;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2T1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.02068576;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2T2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.6491589;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6375;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.NAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.658333333;
        // WHEN
        double result = systemMG1nonPreempCalculatorUnderTest.NAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.295833333;
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
        return features;
    }
}
