package by.training.provider.filter;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.command.enums.UrlEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAccessFilterTest {

    private static final String WRONG_ROLE = "wrongRole";
    private static final String CONTEXT = "gigabit/user";
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private HttpSession session;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn(CONTEXT);
    }

    @Test
    public void shouldChainDoFilterWhenRoleIsUser() throws IOException, ServletException {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn(RoleEnum.USER.getRole());

        UserAccessFilter filter = new UserAccessFilter();
        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldSendRedirectWhenRoleIsNotUser() throws IOException, ServletException {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn(WRONG_ROLE);

        UserAccessFilter filter = new UserAccessFilter();
        filter.doFilter(request, response, chain);

        verify(response).sendRedirect(CONTEXT + UrlEnum.LOGIN_COMMAND.getPage());
    }
}
