package com.unideb.qsa.calculator.implementation.calculator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Unit test for {@link SystemMGamma1KKCalculator}.
 */
public class SystemMGamma1KKCalculatorTest {

    private static final double DELTA = 0.00001;

    private final SystemMGamma1KKCalculator systemMGamma1KKCalculatorUnderTest = new SystemMGamma1KKCalculator();

    @Test
    public void E0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.6666666666666667;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.E0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void SAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.45454545454545;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.SAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void P0Test() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.07022444016780224;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.P0(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void LambdaAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 2.045506231835385695183538569518;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.LambdaAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void aTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.92977555983219776;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.a(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void TAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7999629398870953328744268247022;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.TAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void NAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 1.636329178776409536544307620321;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.NAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void WAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.3454174853420953328744268247022;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.WAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    @Test
    public void QAvgTest() {
        // GIVEN
        Map<SystemFeature, Double> features = createTestFeatures();
        double expected = 0.7065536188521639961117152640377;
        // WHEN
        double result = systemMGamma1KKCalculatorUnderTest.QAvg(features);
        // THEN
        Assert.assertEquals(result, expected, DELTA);
    }

    private Map<SystemFeature, Double> createTestFeatures() {
        Map<SystemFeature, Double> systemMGamma1KK = new HashMap<>();
        systemMGamma1KK.put(SystemFeature.Lambda, 1.5);
        systemMGamma1KK.put(SystemFeature.Mu, 2.2);
        systemMGamma1KK.put(SystemFeature.K, 3.0);
        return systemMGamma1KK;
    }
}
