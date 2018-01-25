package by.training.provider.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {

    private static final String ENCODING = "encoding";
    private static final String UTF_8 = "UTF-8";
    private static final String WRONG_ENCODING = "wrongEncoding";
    @Mock
    private ServletRequest request;
    @Mock
    private ServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private FilterConfig config;

    @Before
    public void setup() {
        when(config.getInitParameter(ENCODING)).thenReturn(UTF_8);
    }

    @Test
    public void shouldSetEncodingWhenItIsNull() throws IOException, ServletException {
        EncodingFilter filter = new EncodingFilter();
        filter.init(config);

        when(request.getCharacterEncoding()).thenReturn(null);

        filter.doFilter(request, response, chain);

        verify(request).setCharacterEncoding(UTF_8);
        verify(response).setCharacterEncoding(UTF_8);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldSetEncodingWhenItIsNotInitEncoding() throws IOException, ServletException {
        EncodingFilter filter = new EncodingFilter();
        filter.init(config);

        when(request.getCharacterEncoding()).thenReturn(WRONG_ENCODING);

        filter.doFilter(request, response, chain);

        verify(request).setCharacterEncoding(UTF_8);
        verify(response).setCharacterEncoding(UTF_8);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldChainDoFilterWhenEncodingIsInitEncoding() throws IOException, ServletException {
        EncodingFilter filter = new EncodingFilter();
        filter.init(config);

        when(request.getCharacterEncoding()).thenReturn(UTF_8);

        filter.doFilter(request, response, chain);

        verify(request, times(0)).setCharacterEncoding(UTF_8);
        verify(response, times(0)).setCharacterEncoding(UTF_8);
        verify(chain).doFilter(request, response);
    }
}
