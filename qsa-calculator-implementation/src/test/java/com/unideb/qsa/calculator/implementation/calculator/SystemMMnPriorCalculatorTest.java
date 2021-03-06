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
        double expected = 0.8703703703703703;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4351851851851852;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3935483870967742;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.06487097716549241;
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
        double expected = 0.04602148825698504;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.11485353334218328;
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
        double expected = 0.058776516284295865;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PNn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNcTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.26391875746714455;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PNc(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.21415194868394233;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2900206126012577;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5134791173923907;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5768716010210809;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.20334723935993101;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.049303504142213815;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.QAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.051347911739239076;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.QAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.11537432020421619;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.QAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4753519018969786;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0737176097303014;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3641183189570286;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.NAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.23653309692442426;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.NAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4857446905745866;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.NAvg3(features);
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
        double expected = 1.5754293389517153;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.231347499035978;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiWrTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.8367873151052352;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiWr(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW90Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6909348489806144;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiW90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW95Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.1844266451510752;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiW95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgWTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7047698330759858;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.WAvgW(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.6393442622950816;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.6874496103198053;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2WW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.066003800535794;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.1418724644531095;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.TAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.3653309692442424;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.TAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.4287234528729327;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.TAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 9.879668400184041;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.ET2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.611296696355697;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT90Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.145466646040044;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiT90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT95Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.803638947465409;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.PiT95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDeltarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.707346690953248;
        // WHEN
        double result = systemMMnPriorCalculatorUnderTest.EDeltar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMMnPrior = new HashMap<>();
        systemMMnPrior.put(SystemFeature.Lambda1, 0.17);
        systemMMnPrior.put(SystemFeature.Lambda2, 0.1);
        systemMMnPrior.put(SystemFeature.Lambda3, 0.2);
        systemMMnPrior.put(SystemFeature.Mu, 0.54);
        systemMMnPrior.put(SystemFeature.c, 2.0);
        systemMMnPrior.put(SystemFeature.n, 3.0);
        systemMMnPrior.put(SystemFeature.r, 98.0);
        systemMMnPrior.put(SystemFeature.t, 2.0);
        return systemMMnPrior;
    }

}
