package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMcKBalkingCalculator}.
 */
public class SystemMMcKBalkingCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMcKBalkingCalculator systemMMcKBalkingCalculatorUnderTest = new SystemMMcKBalkingCalculator();

    @Test
    public void bnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.bn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaNTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.LambdaN(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8120356484;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MunTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.0;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.Mun(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void MuAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8120356484178274;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.MuAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6385306006;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3192653003;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.19658330331;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.Pin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.015414952314138E-4;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.PW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PBTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.762018614919549E-7;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.PB(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PJTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.81203564;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.PJ(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.361469399;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.135337;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.406050083;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EN2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5003987;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.EN2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3354725;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 9.853703298835107E-5;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQ2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0469559755012302E-4;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.EQ2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0468588800325287E-4;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.2134725793118093E-4;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.2134725793118093E-4;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.2134725793118093E-4;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FWtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.99999999;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.FWt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.50012134;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.25004169;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void cAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.405969434;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.cAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.566095656047913;
        // WHEN
        double result = systemMMcKBalkingCalculatorUnderTest.EDeltar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMcKBalking = new HashMap<>();
        systemMMcKBalking.put(SystemFeature.Lambda, 1.0);
        systemMMcKBalking.put(SystemFeature.Mu, 2.0);
        systemMMcKBalking.put(SystemFeature.c, 3.0);
        systemMMcKBalking.put(SystemFeature.K, 5.0);
        systemMMcKBalking.put(SystemFeature.n, 1.0);
        systemMMcKBalking.put(SystemFeature.t, 5.0);
        return systemMMcKBalking;
    }
}
