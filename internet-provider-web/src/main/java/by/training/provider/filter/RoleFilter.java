package by.training.provider.filter;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.RoleEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {

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

        if (role == null || role.isEmpty()) {
            session.setAttribute(ParamNames.ROLE, RoleEnum.GUEST.getRole());
        }

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
    }
}
