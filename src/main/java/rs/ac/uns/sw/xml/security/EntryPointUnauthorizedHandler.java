package rs.ac.uns.sw.xml.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handler for accessing unauthorized entry points.
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    public static final String ACCESS_DENIED = "Access Denied";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ACCESS_DENIED);
    }
}
