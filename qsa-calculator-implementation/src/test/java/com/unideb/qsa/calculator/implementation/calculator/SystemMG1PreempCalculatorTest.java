package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMG1PreempCalculator}.
 */
public class SystemMG1PreempCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMG1PreempCalculator systemMG1PreempCalculatorUnderTest = new SystemMG1PreempCalculator();

    @Test
    public void LambdaTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.1;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.Lambda(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.151219;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.27804878;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.eSPow2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void eSPow3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.349146;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.eSPow3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.06;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2S1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.29;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2S2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2S3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.36;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2S3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.Ro1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.Ro2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void Ro3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.22;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.Ro3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void RoTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.62;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.Ro(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.38;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0625;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.WAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.754166666;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.WAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.6333333;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.WAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.089634;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2W1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2247395;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2W1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2W2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.913342;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2W2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2WTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.20216209;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2W(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.22864583;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.EW2c1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.4821093;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.EW2c2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2c3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 22.8217641;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.EW2c3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void EW2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 7.389464;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.EW2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.0625;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.QAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.5083333;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.QAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.896666;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.QAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 4.4675;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2625;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.TAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.8541666;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.TAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.8333333;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.TAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.2408536;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.284739;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2T1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.20334201;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2T2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2T3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 16.247319;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2T3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void D2TTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 6.49007206;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.D2T(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg1Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.2625;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.NAvg1(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg2Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.70833333;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.NAvg2(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvg3Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 3.11666666;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.NAvg3(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.0875;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void ECostTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 5.153000000000006;
        // WHEN
        double result = systemMG1PreempCalculatorUnderTest.ECost(features);
        // THEN
        Assert.assertEquals(result,expected,  DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> features = new HashMap<>();
        features.put(SystemFeature.Lambda1, 1.0);
        features.put(SystemFeature.eSc1, 0.2);
        features.put(SystemFeature.eSPow2c1, 0.1);
        features.put(SystemFeature.eSPow3c1, 0.05);
        features.put(SystemFeature.Lambda2, 2.0);
        features.put(SystemFeature.eSc2, 0.1);
        features.put(SystemFeature.eSPow2c2, 0.3);
        features.put(SystemFeature.eSPow3c2, 0.6);
        features.put(SystemFeature.Lambda3, 1.1);
        features.put(SystemFeature.eSc3, 0.2);
        features.put(SystemFeature.eSPow2c3, 0.4);
        features.put(SystemFeature.eSPow3c3, 0.165);
        features.put(SystemFeature.CS, 1.0);
        features.put(SystemFeature.CWS, 2.0);
        features.put(SystemFeature.CI, 3.1);
        features.put(SystemFeature.CSR, 1.0);
        features.put(SystemFeature.R, 2.0);
        return features;
    }
}
