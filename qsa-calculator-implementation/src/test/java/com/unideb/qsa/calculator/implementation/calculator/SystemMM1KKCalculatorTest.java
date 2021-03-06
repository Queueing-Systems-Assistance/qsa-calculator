package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMM1KKCalculator}.
 */
public class SystemMM1KKCalculatorTest {

    private static final double DELTA = 0.0001;

    private final SystemMM1KKCalculator systemMM1KKCalculatorUnderTest = new SystemMM1KKCalculator();

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.5;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.833333;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.41666667;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.095238095;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void zTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.z(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.285714;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void USTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.904761905;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.US(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.904761905;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.45238095;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.8095238;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.171429;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.00877;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.190476;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.3922902494331066;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.592105;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinFinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.315789;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.PinFin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FTtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.97850996;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.FTt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void FWtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.9938088;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.FWt(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Pi0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2105263;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.Pi0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EWW0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7500000000000002;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.EWW0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.285714285714286;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQpow2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.714285714285715;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.EQpow2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.0612244897959182;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.416955216;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.590566328;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.97916;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ECostTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.206666666666666;
        // WHEN
        double result = systemMM1KKCalculatorUnderTest.ECost(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.LambdaFin, 1.2);
        features.put(SystemFeature.Mu, 2.4);
        features.put(SystemFeature.n, 2.0);
        features.put(SystemFeature.KFin, 4.0);
        features.put(SystemFeature.t, 3.0);
        features.put(SystemFeature.CS, 2.2);
        features.put(SystemFeature.CWS, 1.3);
        features.put(SystemFeature.CI, 0.5);
        features.put(SystemFeature.CSR, 4.5);
        features.put(SystemFeature.R, 1.1);
        return features;
    }
}
