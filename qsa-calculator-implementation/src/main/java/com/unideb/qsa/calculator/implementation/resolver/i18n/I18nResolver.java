package com.unideb.qsa.calculator.implementation.resolver.i18n;

import java.util.Collection;

import com.amazonaws.services.lambda.invoke.LambdaFunction;

import com.unideb.qsa.calculator.domain.localisation.I18nElement;

/**
 * I18n key resolver.
 */
public interface I18nResolver {

    /**
     * Retrieves i18n keys from the AWS Lambda.
     * @param keys keys
     * @return the resolved i18n key values
     */
    @LambdaFunction
    Collection<I18nElement> resolve(Collection<String> keys);
}
