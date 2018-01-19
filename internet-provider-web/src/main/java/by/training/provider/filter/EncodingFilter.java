package by.training.provider.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Set encoding to init encoding if its not the same yet.
 */
public class EncodingFilter implements Filter {

    private static final String ENCODING = "encoding";
    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        String requestCode = request.getCharacterEncoding();

        if (code != null && !code.equalsIgnoreCase(requestCode)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        code = null;
    }
}
