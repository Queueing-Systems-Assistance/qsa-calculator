package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMcmKBalkingCalculator}.
 */
public class SystemMMcmKBalkingCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMcmKBalkingCalculator systemMMcmKBalkingCalculatorUnderTest = new SystemMMcmKBalkingCalculator();

    @Test
    public void bnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.bn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaNTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.5;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.LambdaN(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.51666666;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MunTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.0;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.Mun(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MuAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.5166666;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.MuAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0666666;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.333333333;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinFinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4265402843;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.PinFin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.09952606;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.PW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PBTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0022985486;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.PB(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PJTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.432820512;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.PJ(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.93333333;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.586111111;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.817361111;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.8263888;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EN2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.35416666;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.EN2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.01847029;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.068055555;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQ2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0875;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.EQ2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.08286844;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.01935229;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0073723;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.00699779;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FWtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.99999999;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.FWt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.51935229;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.25699779;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void cAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.75833333;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.cAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 8.1736111;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 14.0;
        // WHEN
        double result = systemMMcmKBalkingCalculatorUnderTest.EDeltar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMcmKBalking = new HashMap<>();
        systemMMcmKBalking.put(SystemFeature.LambdaFin, 1.0);
        systemMMcmKBalking.put(SystemFeature.Mu, 2.0);
        systemMMcmKBalking.put(SystemFeature.c, 3.0);
        systemMMcmKBalking.put(SystemFeature.m, 5.0);
        systemMMcmKBalking.put(SystemFeature.KFin, 10.0);
        systemMMcmKBalking.put(SystemFeature.n, 1.0);
        systemMMcmKBalking.put(SystemFeature.t, 4.0);
        return systemMMcmKBalking;
    }
}
