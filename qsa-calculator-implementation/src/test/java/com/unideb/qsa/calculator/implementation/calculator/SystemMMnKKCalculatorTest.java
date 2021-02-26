package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMnKKCalculator}.
 */
public class SystemMMnKKCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMnKKCalculator systemMMnKKCalculatorUnderTest = new SystemMMnKKCalculator();

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.666666;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.66666666666666667;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.01328740;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.22145669;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.71801566;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.PW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.1309055;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.827263779527559;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9842519685;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.01479547;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.869094488;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.94242125;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.986712598;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.282726;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3481288;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3916449086;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.PinFin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FTtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.98676339495;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.FTt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FWtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.99954889;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.FWt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4848484848;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.c, 1.0);
        double expected = 0.1;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.eAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.c, 1.0);
        double expected = 33.6296296296;
        // WHEN
        double result = systemMMnKKCalculatorUnderTest.EDelta(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMnKK = new HashMap<>();
        systemMMnKK.put(SystemFeature.LambdaFin, 2.5);
        systemMMnKK.put(SystemFeature.Mu, 1.5);
        systemMMnKK.put(SystemFeature.n, 2.0);
        systemMMnKK.put(SystemFeature.c, 2.0);
        systemMMnKK.put(SystemFeature.KFin, 4.0);
        systemMMnKK.put(SystemFeature.t, 3.0);
        return systemMMnKK;
    }

}
