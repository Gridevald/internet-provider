package by.training.provider.filter;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.RoleEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleFilterTest {

    private static final String EMPTY_STRING = "";
    private static final String VALID_ROLE = "role";
    @Mock
    private HttpServletRequest request;
    @Mock
    private ServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private HttpSession session;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void shouldSetRoleWhenRoleIsNull() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();

        when(session.getAttribute(ParamNames.ROLE)).thenReturn(null);

        filter.doFilter(request, response, chain);

        verify(session).setAttribute(ParamNames.ROLE, RoleEnum.GUEST.getRole());
        verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldSetRoleWhenRoleIsEmpty() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();

        when(session.getAttribute(ParamNames.ROLE)).thenReturn(EMPTY_STRING);

        filter.doFilter(request, response, chain);

        verify(session).setAttribute(ParamNames.ROLE, RoleEnum.GUEST.getRole());
        verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldChainDoFilterWhenRoleIsSet() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();

        when(session.getAttribute(ParamNames.ROLE)).thenReturn(VALID_ROLE);

        filter.doFilter(request, response, chain);

        verify(session, times(0))
                .setAttribute(ParamNames.ROLE, RoleEnum.GUEST.getRole());
        verify(chain).doFilter(request, response);
    }
}
