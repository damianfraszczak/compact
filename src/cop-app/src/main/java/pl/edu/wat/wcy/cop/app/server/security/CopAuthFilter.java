package pl.edu.wat.wcy.cop.app.server.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
// Filters cop auth.
public class CopAuthFilter extends OncePerRequestFilter {
    @Value("${auth.header}")
    public String authHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(authHeader == null){
            authHeader = "OAM_REMOTE_USER";
        }
        String xAuth = request.getHeader(authHeader);

        if (!StringUtils.isNullOrEmpty(xAuth)) {
            // throw new SecurityException("Nie podano danych uwierzytelniajacych");
            // Create our Authentication and let Spring know about it
            Authentication auth = new UsernamePasswordAuthenticationToken(xAuth, xAuth);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }


        filterChain.doFilter(request, response);
    }

}
