package by.molchanov.humanresources.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;

public class RoleTimeTag extends TagSupport {
    private String role = "guest";

    public void setRole(String role) {
        if (!role.isEmpty()) {
            this.role = role;
        }
    }

    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String time = "<hr/>Time : <b> " + gc.getTime() + " </b><hr/>";
        String locale = "Role : <b> " + role + " </b><hr/> ";
        try {
            JspWriter out = pageContext.getOut();
            out.write(time + locale);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
