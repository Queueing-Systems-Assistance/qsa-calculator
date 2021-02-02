package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMHypo1KK2Calculator}.
 */
public class SystemMHypo1KK2CalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMHypo1KK2Calculator systemMHypo1KK2CalculatorUnderTest = new SystemMHypo1KK2Calculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.8333333;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.00171941;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.29079576;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1847246891;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.Pin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9982805;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1210037;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3630111;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5445166;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.84280639;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.63698887;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EN2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.2623179839;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.EN2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.308607643;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.00947306;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.63870829;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQ2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.98662081;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.EQ2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.301255953;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 387.0625;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void KStarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.3636363;
        // WHEN
        double result = systemMHypo1KK2CalculatorUnderTest.KStar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    public Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMHypo1KK = new HashMap<>();
        systemMHypo1KK.put(SystemFeature.Lambda, 1.5);
        systemMHypo1KK.put(SystemFeature.Mu1, 1.2);
        systemMHypo1KK.put(SystemFeature.Mu2, 1.0);
        systemMHypo1KK.put(SystemFeature.K, 3.0);
        systemMHypo1KK.put(SystemFeature.n, 2.0);
        return systemMHypo1KK;
    }
}
