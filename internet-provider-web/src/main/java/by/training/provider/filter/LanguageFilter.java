package by.training.provider.filter;

import by.training.provider.command.ParamNames;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Sets UI language to init language if language is not set yet.
 */
public class LanguageFilter implements Filter {

    private static final String LANG = "lang";
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
        String uiLang = (String) session.getAttribute(ParamNames.UI_LANG);

        if (uiLang == null) {
            session.setAttribute(ParamNames.UI_LANG, language);
        }

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        language = null;
    }
}
