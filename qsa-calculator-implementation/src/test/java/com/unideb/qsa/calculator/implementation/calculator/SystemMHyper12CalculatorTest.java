package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMHyper12Calculator}.
 */
public class SystemMHyper12CalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMHyper12Calculator systemMHyper12CalculatorUnderTest = new SystemMHyper12Calculator();

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.871212;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.87121212;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.12878787878787878;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void C2STest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0037807;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.C2S(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 52.729638;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 51.096546;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 45.953792;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TLCFSTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 516.890923;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.D2TLCFS(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2TSIROTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 106.980579;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.D2TSIRO(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 45.191912;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 91.86589;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.ET2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 80.056621;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.7774936;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.775846;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PN1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8712121;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.PN1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.9046345;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.7758467;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.9046345;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ENdDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.7647058;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.ENdDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2NdDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 764.51455;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.D2NdDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.764705;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void VarDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 666.227355;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.VarDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void PiT90ApprTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 15.588446;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.PiT90Appr(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void PiT95ApprTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 20.3336919;
        // WHEN
        double result = systemMHyper12CalculatorUnderTest.PiT95Appr(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.Lambda, 1.0);
        features.put(SystemFeature.Mu1, 1.1);
        features.put(SystemFeature.Mu2, 1.2);
        features.put(SystemFeature.p1, 0.5);
        features.put(SystemFeature.p2, 0.5);
        features.put(SystemFeature.CS, 1.0);
        features.put(SystemFeature.CWS, 2.0);
        features.put(SystemFeature.CI, 3.1);
        features.put(SystemFeature.CSR, 1.0);
        features.put(SystemFeature.R, 2.0);
        return features;
    }
}
