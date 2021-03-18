package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMHypo12Calculator}.
 */
public class SystemMHypo12CalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMHypo12Calculator systemMHypo12CalculatorUnderTest = new SystemMHypo12Calculator();

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.924242;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.924242;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.07575757575757569;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void C2STest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.822628325;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.C2S(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 134.64;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 133.0130486;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 123.44;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TLCFSTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2699.16241;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.D2TLCFS(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2TSIROTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 318.90683;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.D2TSIRO(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 122.737291;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 248.88;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.ET2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 228.328484;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 11.1180327;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 11.2;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PN1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.924242;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.PN1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 10.2757575;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 11.2;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 10.27575;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ENdDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 13.2;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.ENdDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2NdDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3741.936;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.D2NdDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 12.2;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void VarDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3432.056;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.VarDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void PiT90ApprTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 25.64346;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.PiT90Appr(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void PiT95ApprTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 33.420711;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.PiT95Appr(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void ECostTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 22.6348484848485;
        // WHEN
        double result = systemMHypo12CalculatorUnderTest.ECost(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.Lambda, 1.0);
        features.put(SystemFeature.Mu1, 11.0);
        features.put(SystemFeature.Mu2, 1.2);
        features.put(SystemFeature.CS, 1.0);
        features.put(SystemFeature.CWS, 2.0);
        features.put(SystemFeature.CI, 3.1);
        features.put(SystemFeature.CSR, 1.0);
        features.put(SystemFeature.R, 2.0);
        return features;
    }
}
