package ma.cigma.pfe.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 3:09 PM
 */

@Slf4j
@Component
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, PATCH");
        response.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Accept, Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authortization");
        response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin, Access-Control-All-Credentials,Authortization");

        if(request.getMethod().equals("OPTIONS")){

            response.setStatus(HttpServletResponse.SC_OK);
        }else{

            try {
                String jwt = parseJwt(request);
                if(jwt != null && jwtUtils.validateJwtToken(jwt)){

                    String username = jwtUtils.getUsernameFromJwtToken(jwt);
                    log.info("username : {}",username);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            } catch (Exception e) {
                log.error("cannot set user authentication: {}",e);
            }

            filterChain.doFilter(request,response);

        }

    }

    private String parseJwt(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            log.info("JWT token : {}", authHeader);
            return authHeader.substring(7);
        }
        return null;
    }
}
