package com.unideb.qsa.calculator.server.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.unideb.qsa.calculator.domain.error.ErrorResponse;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.domain.exception.QSAMessageException;
import com.unideb.qsa.calculator.domain.exception.QSAValidationException;
import com.unideb.qsa.calculator.implementation.service.ErrorService;

/**
 * Advice for exceptions, errors.
 */
@ControllerAdvice
public class ExceptionHandlingAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlingAdvice.class);
    private static final String ERROR_GLOBAL_PARSE_INPUT = "error.global.parseInput";
    private static final String ERROR_GLOBAL_NO_URL = "error.global.noURL";
    private static final String EXCEPTION_OCCURRED_MESSAGE = "Exception occurred [MESSAGE] [{}]";
    private static final String EXCEPTION_OCCURRED_VALIDATION = "Exception occurred [VALIDATION] {}";
    private static final String EXCEPTION_OCCURRED_INTERNAL = "Exception occurred [INTERNAL]";

    @Autowired
    private ErrorService errorService;

    /**
     * Exception handler for {@link Throwable}.
     * @param exception the exception
     * @return A readable error
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception exception) {
        LOG.error(EXCEPTION_OCCURRED_INTERNAL, exception);
        ErrorResponse errorResponse = errorService.createErrorResponse(ERROR_GLOBAL_PARSE_INPUT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Exception handler for {@link Throwable}.
     * @param exception the exception
     * @return A readable error
     */
    @ExceptionHandler(QSAMessageException.class)
    public ResponseEntity<ErrorResponse> handleMessageExceptions(Exception exception) {
        LOG.warn(EXCEPTION_OCCURRED_MESSAGE, exception.getMessage());
        ErrorResponse errorResponse = errorService.createErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Exception handler for {@link QSAValidationException}.
     * @param exception the exception
     * @return Validation response with feature ids and error messages
     */
    @ExceptionHandler(QSAValidationException.class)
    public ResponseEntity<List<ValidationErrorResponse>> handleValidationExceptions(QSAValidationException exception) {
        List<ValidationErrorResponse> validationErrorResponses = exception.getValidationErrorResponses();
        LOG.warn(EXCEPTION_OCCURRED_VALIDATION, getValidationErrorI18nKeys(validationErrorResponses));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorService.createErrorResponse(validationErrorResponses));
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status,
            WebRequest webRequest) {
        return ResponseEntity.status(status).body(errorService.createErrorResponse(ERROR_GLOBAL_PARSE_INPUT));
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return ResponseEntity.status(status).body(errorService.createErrorResponse(ERROR_GLOBAL_NO_URL));
    }

    private List<String> getValidationErrorI18nKeys(List<ValidationErrorResponse> validationErrorResponses) {
        return validationErrorResponses.stream()
                                       .map(ValidationErrorResponse::getErrorMessage)
                                       .collect(Collectors.toList());
    }
}
