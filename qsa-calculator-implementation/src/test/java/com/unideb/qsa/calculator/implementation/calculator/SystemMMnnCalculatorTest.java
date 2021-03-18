package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMnnCalculator}.
 */
public class SystemMMnnCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMnnCalculator systemMMnnCalculatorUnderTest = new SystemMMnnCalculator();

    @Test
    public void BcRoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.012658;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.BcRo(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void BcRoAppr1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.01674735;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.BcRoAppr1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void BcRoAppr2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.00232784;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.BcRoAppr2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void BcRoAppr3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.00252656;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.BcRoAppr3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FTtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.35596;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.FTt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.21721;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.49367;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.477808;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.07692;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.27272;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.16456;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UsTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3924;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 11.5384;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.eAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.9356;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.EDeltar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ECostTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 21.759299471238585;
        // WHEN
        double result = systemMMnnCalculatorUnderTest.ECost(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.Lambda, 0.22);
        features.put(SystemFeature.Mu, 0.44);
        features.put(SystemFeature.c, 3.0);
        features.put(SystemFeature.n, 2.0);
        features.put(SystemFeature.t, 1.0);
        features.put(SystemFeature.CS, 2.2);
        features.put(SystemFeature.CWS, 1.3);
        features.put(SystemFeature.CI, 0.5);
        features.put(SystemFeature.CSR, 4.5);
        features.put(SystemFeature.CLC, 1.2);
        features.put(SystemFeature.R, 1.1);
        return features;
    }

}
