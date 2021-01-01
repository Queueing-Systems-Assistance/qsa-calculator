package com.unideb.qsa.calculator.implementation.resolver;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.Assert.assertEquals;

import java.util.Locale;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for {@link MessageResolver}.
 */
public class MessageResolverTest {

    private static final String RESOLVED_MESSAGE = "message";
    private static final String I18N_KEY = "i18n.key.sample";
    private static final String[] ARGUMENTS = new String[]{"arg1"};
    private static final Locale LOCALE = LocaleContextHolder.getLocale();

    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private MessageResolver messageResolver;

    @BeforeMethod
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void getStringReturnsCorrectValue() {
        // GIVEN
        given(messageSource.getMessage(I18N_KEY, null, LOCALE)).willReturn(RESOLVED_MESSAGE);
        // WHEN
        String actual = messageResolver.getString(I18N_KEY);
        // THEN
        verify(messageSource).getMessage(I18N_KEY, null, LOCALE);
        assertEquals(RESOLVED_MESSAGE, actual);
    }

    @Test
    public void getStringReturnsCorrectValueWithArguments() {
        // GIVEN
        given(messageSource.getMessage(I18N_KEY, ARGUMENTS, LOCALE)).willReturn(RESOLVED_MESSAGE);
        // WHEN
        String actual = messageResolver.getString(I18N_KEY, ARGUMENTS);
        // THEN
        verify(messageSource).getMessage(I18N_KEY, ARGUMENTS, LOCALE);
        assertEquals(RESOLVED_MESSAGE, actual);
    }
}
