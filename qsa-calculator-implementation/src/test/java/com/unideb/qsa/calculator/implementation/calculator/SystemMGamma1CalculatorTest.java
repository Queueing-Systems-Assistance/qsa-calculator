package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMGamma1Calculator}.
 */
public class SystemMGamma1CalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMGamma1Calculator systemMGamma1CalculatorUnderTest = new SystemMGamma1Calculator();

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.55;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.55;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void C2STest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9090909;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.C2S(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.541458;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.7164583;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.34979166;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TLCFSTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.081257;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.D2TLCFS(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2TSIROTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.638647;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.D2TSIRO(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0747916;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ET2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.7698611;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.ET2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.4865277;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.1666666;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.19166666;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PN1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.55;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.PN1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6416666;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.1916666;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.64166666;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ENdDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.22222222;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.ENdDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void D2NdDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 9.053497;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.D2NdDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.2222222;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    @Test
    public void VarDeltaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.84362139;
        // WHEN
        double result = systemMGamma1CalculatorUnderTest.VarDelta(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.Lambda, 1.0);
        features.put(SystemFeature.Alpha, 1.1);
        features.put(SystemFeature.Mu, 2.0);

        return features;
    }
}
