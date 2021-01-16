package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMnnKCalculator}.
 */
public class SystemMMnnKCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMnnKCalculator systemMMnnKCalculatorUnderTest = new SystemMMnnKCalculator();

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.33333;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0726;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.363;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.Pin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.927419;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6451612;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.16129032;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.66666;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.93548;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 8.06451;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.806451;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ETauTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.944444;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.ETau(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ENRTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.388888;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.ENR(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PBKcTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.28;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.PBKc(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.388888;
        // WHEN
        double result = systemMMnnKCalculatorUnderTest.EDeltar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMnn = new HashMap<>();
        systemMMnn.put(SystemFeature.Lambda, 0.2);
        systemMMnn.put(SystemFeature.Mu, 0.6);
        systemMMnn.put(SystemFeature.c, 3.0);
        systemMMnn.put(SystemFeature.K, 10.0);
        systemMMnn.put(SystemFeature.n, 2.0);
        return systemMMnn;
    }
}
