package com.unideb.qsa.calculator.implementation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;

import com.unideb.qsa.calculator.implementation.resolver.i18n.I18nResolver;

/**
 * Configuration for AWS.
 */
@Configuration
public class AwsConfig {

    @Value("${qsa.aws.access-key}")
    private String awsAccessKey;
    @Value("${qsa.aws.secret-key}")
    private String awsSecretKey;
    @Value("${qsa.aws.i18n-lambda-name}")
    private String i18nLambdaName;

    @Bean
    public I18nResolver i18nAWSLambdaResolver(AWSLambda awsLambda) {
        return LambdaInvokerFactory.builder()
                                   .lambdaClient(awsLambda)
                                   .lambdaFunctionNameResolver((method, annotation, config) -> i18nLambdaName)
                                   .build(I18nResolver.class);
    }

    @Bean
    public AWSLambda createAWSLambdaClient() {
        return AWSLambdaClient.builder()
                              .withRegion(Regions.EU_CENTRAL_1)
                              .withCredentials(new AWSStaticCredentialsProvider(createCredentials()))
                              .build();
    }

    private BasicAWSCredentials createCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }
}
