package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMcmKBalkingRenegingCalculator}.
 */
public class SystemMMcmKBalkingRenegingCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMcmKBalkingRenegingCalculator systemMMcmKBalkingRenegingCalculatorUnderTest = new SystemMMcmKBalkingRenegingCalculator();

    @Test
    public void bnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.bn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void rnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.rn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaNTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.5;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.LambdaN(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.549671977;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MunTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.0;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.Mun(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MuAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.54967197;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.MuAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0674789128;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.337394564;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.42772277;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.Pin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.097029702;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.PW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PBTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.001373249;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.PB(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PJTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.43422184;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.PJ(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PRTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.017821782;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.PR(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.93252108;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.58106841;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.82040768;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.795923149;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EN2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.1764292;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.EN2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.95108928;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.86340206;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQ2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.517104;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.EQ2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7716409;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.243234323;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.50594059;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void cAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.74320524;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.cAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 8.2040768;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void rAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0632614;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.rAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 13.8194444;
        // WHEN
        double result = systemMMcmKBalkingRenegingCalculatorUnderTest.EDeltar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMcmK = new HashMap<>();
        systemMMcmK.put(SystemFeature.Lambda, 1.0);
        systemMMcmK.put(SystemFeature.Mu, 2.0);
        systemMMcmK.put(SystemFeature.c, 3.0);
        systemMMcmK.put(SystemFeature.m, 5.0);
        systemMMcmK.put(SystemFeature.K, 10.0);
        systemMMcmK.put(SystemFeature.Theta, 1.2);
        systemMMcmK.put(SystemFeature.n, 1.0);
        return systemMMcmK;
    }
}
