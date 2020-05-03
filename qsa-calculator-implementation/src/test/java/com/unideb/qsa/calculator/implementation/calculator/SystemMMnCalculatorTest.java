package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMnCalculator}.
 */
public class SystemMMnCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMnCalculator systemMMnCalculatorUnderTest = new SystemMMnCalculator();

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMnCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.25;
        // WHEN
        double result = systemMMnCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.25;
        // WHEN
        double result = systemMMnCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6;
        // WHEN
        double result = systemMMnCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.01875;
        // WHEN
        double result = systemMMnCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.025;
        // WHEN
        double result = systemMMnCalculatorUnderTest.PNn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNcTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1;
        // WHEN
        double result = systemMMnCalculatorUnderTest.PNc(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.10752688;
        // WHEN
        double result = systemMMnCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0333333;
        // WHEN
        double result = systemMMnCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.05444444;
        // WHEN
        double result = systemMMnCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.533333;
        // WHEN
        double result = systemMMnCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.604444444;
        // WHEN
        double result = systemMMnCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9;
        // WHEN
        double result = systemMMnCalculatorUnderTest.WAvg0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FWtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.993857878;
        // WHEN
        double result = systemMMnCalculatorUnderTest.FWt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2196785;
        // WHEN
        double result = systemMMnCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiWrTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.751580649;
        // WHEN
        double result = systemMMnCalculatorUnderTest.PiWr(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW90Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0;
        // WHEN
        double result = systemMMnCalculatorUnderTest.PiW90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW95Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.323688167;
        // WHEN
        double result = systemMMnCalculatorUnderTest.PiW95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.938578786;
        // WHEN
        double result = systemMMnCalculatorUnderTest.WAvgW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0752688;
        // WHEN
        double result = systemMMnCalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.156203029;
        // WHEN
        double result = systemMMnCalculatorUnderTest.D2WW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FTtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8254770863414033;
        // WHEN
        double result = systemMMnCalculatorUnderTest.FTt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.72043;
        // WHEN
        double result = systemMMnCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.78101514;
        // WHEN
        double result = systemMMnCalculatorUnderTest.ET2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.8211353;
        // WHEN
        double result = systemMMnCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT90Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.903940769;
        // WHEN
        double result = systemMMnCalculatorUnderTest.PiT90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT95Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.07967727;
        // WHEN
        double result = systemMMnCalculatorUnderTest.PiT95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMn = new HashMap<>();
        systemMMn.put(SystemFeature.Lambda, 0.31);
        systemMMn.put(SystemFeature.Mu, 0.62);
        systemMMn.put(SystemFeature.c, 2.0);
        systemMMn.put(SystemFeature.n, 3.0);
        systemMMn.put(SystemFeature.r, 98.0);
        systemMMn.put(SystemFeature.t, 3.0);
        return systemMMn;
    }

}
