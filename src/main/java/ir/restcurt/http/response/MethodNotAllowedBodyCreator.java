package ir.restcurt.http.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Hamid Samani
 */
public class MethodNotAllowedBodyCreator extends AbstractResponseBodyCreator {
    private static final String NOT_ALLOWED = "<p>Requested Method Not Allowed</p>";

    public MethodNotAllowedBodyCreator(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void buildResponse() {
        response.setStatus(405);
        try {
            PrintWriter writer = response.getWriter();
            writer.println(HEADER);
            writer.println(NOT_ALLOWED);
            writer.println(FOOTER);
        } catch (IOException e) {
            //ignore exception
        }

    }
}
