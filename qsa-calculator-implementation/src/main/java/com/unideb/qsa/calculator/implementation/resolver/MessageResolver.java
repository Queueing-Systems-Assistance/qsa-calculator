package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import com.unideb.qsa.calculator.domain.exception.QSAServerException;
import com.unideb.qsa.calculator.domain.localisation.I18nElement;

/**
 * Localisation configuration.
 */
@Component
public class MessageResolver {

    private static final String REQUEST_ID = "X-Request-Id";
    private static final String MDC_REQUEST_ID = "requestId";
    private static final String DEFAULT_EN_US_LOCALE = "en_US";
    private static final String ERROR_NO_RESPONSE = "No response arrived from [qsa-localisation-service], key [%s]";
    private static final String ERROR_RESPONSE_I18N_SERVICE = "Error response from i18n-service, status code [%s]";
    private static final String ERROR_REQUESTED_AND_RESOLVED_KEYS_NOT_EQUAL = "Resolved keys are not the same as requested ones: [requestedKeys=%s, resolvedKeys=%s]";
    private static final int FIRST_ELEMENT = 0;

    @Autowired
    private WebClient webClient;
    @Value("${qsa.i18n-service.uri}")
    private String i18nServiceUri;

    /**
     * Resolve i18n keys with its values based on the current locale (eg. en_US).
     * @param keys i18n keys
     * @return a map, where the key is the i18n key, value is the corresponding value
     */
    public Map<String, String> resolveKeyValuePairs(Collection<String> keys) {
        List<I18nElement> resolveI18nKeys = resolveI18nKeys(keys);
        return resolveI18nKeys.stream().collect(Collectors.toMap(
                I18nElement::getKey,
                i18nElement -> i18nElement.getValue().get(getCurrentLocale(i18nElement.getValue())))
        );
    }

    /**
     * Resolve an i18n key value based on the current locale (eg. en_US).
     * @param key i18n key
     * @return localised message
     */
    public String resolve(String key) {
        return resolve(List.of(key)).get(FIRST_ELEMENT);
    }

    /**
     * Resolve i18n keys based on the current locale (eg. en_US).
     * @param keys i18n keys
     * @return localised messages
     */
    public List<String> resolve(Collection<String> keys) {
        List<I18nElement> resolveI18nKeys = resolveI18nKeys(keys);
        return resolveI18nKeys.stream()
                              .map(I18nElement::getValue)
                              .map(values -> values.get(getCurrentLocale(values)))
                              .collect(Collectors.toList());
    }

    private String getCurrentLocale(Map<String, String> values) {
        return values.keySet().stream()
                     .filter(LocaleContextHolder.getLocale().toString()::equalsIgnoreCase)
                     .findFirst()
                     .orElse(DEFAULT_EN_US_LOCALE);
    }

    private List<I18nElement> resolveI18nKeys(Collection<String> keys) {
        List<I18nElement> resolvedI18nKeys = webClient.post()
                                                      .uri(i18nServiceUri)
                                                      .headers(httpHeaders -> httpHeaders.addAll(getHeaders()))
                                                      .bodyValue(keys)
                                                      .retrieve()
                                                      .onStatus(HttpStatus::isError, this::createError)
                                                      .bodyToMono(new ParameterizedTypeReference<List<I18nElement>>() {})
                                                      .blockOptional()
                                                      .orElseThrow(() -> new QSAServerException(String.format(ERROR_NO_RESPONSE, keys)));
        if (resolvedI18nKeys.size() != keys.size()) {
            throw new QSAServerException(String.format(ERROR_REQUESTED_AND_RESOLVED_KEYS_NOT_EQUAL, keys, resolvedI18nKeys));
        }
        return resolvedI18nKeys;
    }

    private Mono<Throwable> createError(ClientResponse clientResponse) {
        return Mono.error(new QSAServerException(String.format(ERROR_RESPONSE_I18N_SERVICE, clientResponse.statusCode())));
    }

    private HttpHeaders getHeaders() {
        var httpHeaders = new LinkedMultiValueMap<String, String>();
        httpHeaders.add(REQUEST_ID, MDC.get(MDC_REQUEST_ID));
        return new HttpHeaders(httpHeaders);
    }
}
