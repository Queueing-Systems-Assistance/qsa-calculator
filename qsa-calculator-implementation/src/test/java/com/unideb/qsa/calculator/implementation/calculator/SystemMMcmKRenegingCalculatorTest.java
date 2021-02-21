package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMcKRenegingCalculator}.
 */
public class SystemMMcmKRenegingCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMcmKRenegingCalculator systemMMcmKRenegingCalculatorUnderTest = new SystemMMcmKRenegingCalculator();

    @Test
    public void rnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.rn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaNTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 9.0;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.LambdaN(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.895196506;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MunTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.0;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.Mun(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MuAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.895196506;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.MuAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.017467248;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.087336244;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.13333333;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.Pin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.57037037;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.PW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PBTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1336898;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.PB(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PJTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.PJ(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PRTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.12592592;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.PR(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9825327;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8588064;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6804949;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.1950509;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EN2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 11.8558951;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.EN2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.647544;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.21251819;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQ2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.44832605;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.EQ2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.5530892;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.37530864;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5419753;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void cAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.57641921;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.cAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.80494905;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void rAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.742358;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.rAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 56.249999;
        // WHEN
        double result = systemMMcmKRenegingCalculatorUnderTest.EDeltar(features);
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
