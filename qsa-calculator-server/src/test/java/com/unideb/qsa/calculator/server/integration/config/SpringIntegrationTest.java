package com.unideb.qsa.calculator.server.integration.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import com.unideb.qsa.calculator.server.Application;

/**
 * Cucumber configuration for integration tests.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberOptions(features = "src/test/resources", glue = "com.unideb.qsa.calculator.server")
public class SpringIntegrationTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
