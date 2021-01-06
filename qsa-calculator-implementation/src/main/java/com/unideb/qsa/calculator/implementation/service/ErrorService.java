package com.unideb.qsa.calculator.implementation.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.error.ErrorResponse;
import com.unideb.qsa.calculator.implementation.resolver.MessageResolver;

/**
 * Facade for error handling.
 */
@Component
public class ErrorService {

    private static final String ERROR_GLOBAL_INTERNAL = "error.global.internal";

    @Autowired
    private MessageResolver messageResolver;

    /**
     * Create {@link ErrorResponse} based on message.
     * @param errorKey error message key
     * @return created error response
     */
    public ErrorResponse createErrorResponse(String errorKey) {
        String readableMessage = getReadableMessage(errorKey);
        return new ErrorResponse(readableMessage);
    }

    /**
     * Create {@link List} based on validation errors.
     * @param validationErrors errors with i18n keys
     * @return List of resolved error messages
     */
    public ErrorResponse resolveAndUpdateI18nKey(Map<String, List<String>> validationErrors) {
        validationErrors.keySet().forEach(key -> updateI18nKeys(validationErrors, key));
        return new ErrorResponse("Validáció során hiba történt", validationErrors);
    }

    private void updateI18nKeys(Map<String, List<String>> extensions, String key) {
        extensions.put(key, extensions.get(key).stream()
                                      .map(messageResolver::getString)
                                      .collect(Collectors.toList()));
    }

    private String getReadableMessage(String errorKey) {
        String filteredErrorKey = Optional.ofNullable(errorKey).orElse(ERROR_GLOBAL_INTERNAL);
        return messageResolver.getString(filteredErrorKey);
    }
}
