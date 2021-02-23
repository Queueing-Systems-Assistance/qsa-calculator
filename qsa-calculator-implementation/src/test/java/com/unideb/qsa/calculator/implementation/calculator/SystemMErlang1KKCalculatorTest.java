package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMErlang1KKCalculator}.
 */
public class SystemMErlang1KKCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMErlang1KKCalculator systemMErlang1KKCalculatorUnderTest = new SystemMErlang1KKCalculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> systemMErlang1KK = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.E0(systemMErlang1KK);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.363636363;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0029883;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PnTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3690421806;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.Pn(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void PinFinTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.22448566;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.PinFin(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7311418;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.997011618;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void UtTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.16247596;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.Ut(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void mAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.4874279;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.mAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.43650426;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.51257209;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EN2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.69021135;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.EN2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2NTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.377192809;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.D2N(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.0728679;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.51556047;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EQ2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.66207877;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.EQ2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2QTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.36515521;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.D2Q(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EDelta1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 222.4195089;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.EDelta1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void KStarTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.48888888;
        // WHEN
        double result = systemMErlang1KKCalculatorUnderTest.KStar(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.nErlang, 3.0);
        features.put(SystemFeature.LambdaFin, 1.5);
        features.put(SystemFeature.Mu, 2.2);
        features.put(SystemFeature.KFin, 3.0);
        features.put(SystemFeature.n, 2.0);
        return features;
    }
}
