package ir.restcurt.http.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Hamid Samani
 */
public class ResponseNotFoundBodyCreator extends AbstractResponseBodyCreator {
    private static final String NOT_FOUND = "<p>404 Error, Requested Resource Not Found</p>";

    public ResponseNotFoundBodyCreator(HttpServletResponse response) {
        super(response);
    }

    public void buildResponse() {
        response.setStatus(404);
        try {
            PrintWriter writer = response.getWriter();
            writer.println(HEADER);
            writer.println(NOT_FOUND);
            writer.println(FOOTER);
        } catch (IOException e) {
            //ignore exception
        }

    }
}
