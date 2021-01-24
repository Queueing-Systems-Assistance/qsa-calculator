package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMMnPriorCalculator}.
 */
public class SystemMMnPriorCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMMnPriorCalculator systemMMnPriorCalculatorUnderTest = new SystemMMnPriorCalculator();

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.25;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.01875;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnWhenCisBiggerThanNTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.c, 6.0);
        double expected = 0.012635;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.025;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PNn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNnWhenCisBiggerThanNTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.c, 4.0);
        double expected = 0.014440433;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PNn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNcTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PNc(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.12345679;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1098901;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1465201;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.033333;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0186813;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.QAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.014652;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.QAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.05444;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.53333;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.333496;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.NAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1998371;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.NAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.851851;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.60444;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.28959;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiWrTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.862925;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiWr(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW90Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiW90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW95Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.37164196;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiW95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8021013;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvgW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.234567;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.5241579;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2WW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.9753086;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.9617419;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.TAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.9983719;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.TAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.620789;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.ET2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.7189452;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT90Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.4823023;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiT90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT95Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.832222;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiT95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.4691358;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.EDeltar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMnPrior = new HashMap<>();
        systemMMnPrior.put(SystemFeature.Lambda1, 0.17);
        systemMMnPrior.put(SystemFeature.Lambda2, 0.1);
        systemMMnPrior.put(SystemFeature.Mu, 0.54);
        systemMMnPrior.put(SystemFeature.c, 2.0);
        systemMMnPrior.put(SystemFeature.n, 3.0);
        systemMMnPrior.put(SystemFeature.r, 98.0);
        systemMMnPrior.put(SystemFeature.t, 2.0);
        return systemMMnPrior;
    }

}
