package by.training.provider.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {

    private String language;

    @Override
    public void init(FilterConfig filterConfig) {
        language = filterConfig.getInitParameter("lang");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        String uiLang = (String) session.getAttribute("uiLang");

        if (uiLang == null) {
            session.setAttribute("uiLang", language);
        }

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        language = null;
    }
}
