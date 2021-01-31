package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMHyper1KK3Calculator}.
 */
public class SystemMHyper1KK3CalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMHyper1KK3Calculator systemMHyper1KK3CalculatorUnderTest = new SystemMHyper1KK3Calculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.09166666;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.021188364;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.97881163;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.199249187;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5977475;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8966213;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.67922735;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.4022524;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.5875606;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.4234408;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 30.797142;
        // WHEN
        double result = systemMHyper1KK3CalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMHyper1KK = new HashMap<>();
        systemMHyper1KK.put(SystemFeature.Lambda, 1.5);
        systemMHyper1KK.put(SystemFeature.Mu1, 1.2);
        systemMHyper1KK.put(SystemFeature.Mu2, 1.0);
        systemMHyper1KK.put(SystemFeature.Mu3, 0.8);
        systemMHyper1KK.put(SystemFeature.p1, 0.2);
        systemMHyper1KK.put(SystemFeature.p2, 0.3);
        systemMHyper1KK.put(SystemFeature.p3, 0.5);
        systemMHyper1KK.put(SystemFeature.K, 3.0);
        return systemMHyper1KK;
    }
}
