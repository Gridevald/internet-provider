package by.training.provider.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {

    private static final String LANG = "lang";
    private static final String UI_LANG = "uiLang";
    private String language;

    @Override
    public void init(FilterConfig filterConfig) {
        language = filterConfig.getInitParameter(LANG);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        String uiLang = (String) session.getAttribute(UI_LANG);

        if (uiLang == null) {
            session.setAttribute(UI_LANG, language);
        }

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        language = null;
    }
}
