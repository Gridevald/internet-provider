package by.training.provider.filter;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.command.enums.RoleEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Redirects to login page if role is not 'user' and
 * visitor tying to access user pages.
 */
public class UserAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String role = (String) session.getAttribute(ParamNames.ROLE);

        if (!role.equals(RoleEnum.USER.getRole())) {
            String contextPath = httpRequest.getContextPath();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(contextPath + UrlEnum.LOGIN_COMMAND.getPage());
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
