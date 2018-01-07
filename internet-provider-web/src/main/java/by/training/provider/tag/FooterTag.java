package by.training.provider.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {

    private static final String FOOTER = "<footer>" +
            "<p class=\"copyright\">Copyright \u00A9 Pavel Yudzitski</p>" +
            "</footer>";

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter writer = pageContext.getOut();
            writer.write(FOOTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
