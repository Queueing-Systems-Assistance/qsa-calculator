package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMM1nonPreempCalculator}.
 */
public class SystemMM1nonPreempCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMM1nonPreempCalculator systemMM1nonPreempCalculatorUnderTest = new SystemMM1nonPreempCalculator();

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.9382;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QQ0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.9382;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.D2QQ0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.8886;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 123.4567;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 107.4567;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQQ0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.7777;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.EQQ0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FTtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0860;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.FTt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FWtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4151;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.FWt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.7777;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.28190476190476;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.NAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.4958730158730;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.NAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PNnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.64;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PNn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PTtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9139;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PTt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PWtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5849;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PWt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT90Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 25.5842;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PiT90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiT95Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 33.28588;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PiT95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiTrTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1116;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PiTr(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW90TestReturnsMax() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 20.6255;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PiW90(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiW95TestReturnsMax() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 28.3271;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PiW95(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiWrTestReturnsMax() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        features.put(SystemFeature.r, 45.0);
        double expected = 1.6838;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PiWr(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PiWrTestReturnsZero() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.PiWr(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2304;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.1377;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.1219;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.QAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.01587;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.QAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.64;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.64;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.48;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.Ro2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.16;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.Ro1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 11.1111;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.0476190476190;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.TAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 12.4656084656;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.TAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.1111;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.047619047619047;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.WAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 8.4656;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.WAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Edelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 11.111111;
        // WHEN
        double result = systemMM1nonPreempCalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMM1nonPreemp = new HashMap<>();
        systemMM1nonPreemp.put(SystemFeature.Lambda1, 0.04);
        systemMM1nonPreemp.put(SystemFeature.Lambda2, 0.12);
        systemMM1nonPreemp.put(SystemFeature.Mu, 0.25);
        systemMM1nonPreemp.put(SystemFeature.n, 1.0);
        systemMM1nonPreemp.put(SystemFeature.r, 1.0);
        systemMM1nonPreemp.put(SystemFeature.t, 1.0);
        return systemMM1nonPreemp;
    }

}
