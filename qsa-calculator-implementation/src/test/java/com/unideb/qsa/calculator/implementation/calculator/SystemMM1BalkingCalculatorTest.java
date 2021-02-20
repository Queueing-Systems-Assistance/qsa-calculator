package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMM1BalkingCalculator}.
 */
public class SystemMM1BalkingCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMM1BalkingCalculator systemMM1BalkingCalculatorUnderTest = new SystemMM1BalkingCalculator();

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void bnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.33333333;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.bn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaNTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.11;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.LambdaN(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.25968976;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1321205588;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.586018274285;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.29033416;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0758163324640791779504749418739;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6065306597;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.393469340;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1065306597;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.5151515151515151;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.92537430495;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4102227898;
        // WHEN
        double result = systemMM1BalkingCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMM1Dec = new HashMap<>();
        systemMM1Dec.put(SystemFeature.Lambda, 0.33);
        systemMM1Dec.put(SystemFeature.Mu, 0.66);
        systemMM1Dec.put(SystemFeature.n, 2.0);
        systemMM1Dec.put(SystemFeature.t, 1.0);
        return systemMM1Dec;
    }

}
