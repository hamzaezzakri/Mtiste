package ma.cigma.pfe.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 3:07 PM
 */

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.error("Unauthorized error: {}",authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error: Unauthorized");
    }
}
