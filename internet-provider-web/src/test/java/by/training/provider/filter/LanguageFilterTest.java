package by.training.provider.filter;

import by.training.provider.command.ParamNames;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LanguageFilterTest {

    private static final String LANG = "lang";
    private static final String DEFAULT_LANG = "ru";
    private static final String VALID_LANG = "validLang";
    @Mock
    private HttpServletRequest request;
    @Mock
    private ServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private FilterConfig config;
    @Mock
    private HttpSession session;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(config.getInitParameter(LANG)).thenReturn(DEFAULT_LANG);
    }

    @Test
    public void shouldSetDefaultLangWhenUILangIsNull() throws IOException, ServletException {
        LanguageFilter filter = new LanguageFilter();
        filter.init(config);

        when(session.getAttribute(ParamNames.UI_LANG)).thenReturn(null);

        filter.doFilter(request, response, chain);

        verify(session).setAttribute(ParamNames.UI_LANG, DEFAULT_LANG);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldChainDoFilterWhenUILangIsSet() throws IOException, ServletException {
        LanguageFilter filter = new LanguageFilter();
        filter.init(config);

        when(session.getAttribute(ParamNames.UI_LANG)).thenReturn(VALID_LANG);

        filter.doFilter(request, response, chain);

        verify(session, times(0)).setAttribute(ParamNames.UI_LANG, DEFAULT_LANG);
        verify(chain).doFilter(request, response);
    }
}
