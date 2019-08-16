package just.fo.fun.filter;

import just.fo.fun.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
@Component
public class TokenAuthenticationFilter implements Filter {

    @Autowired
    private AuthService authService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            String token = getToken((HttpServletRequest) servletRequest);
            authService.authenticate(token);
        } catch (AuthenticationException e) {
            log.warn("Token auth failed", e);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

    public static String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authorization)) {
            authorization = request.getParameter("token");
            if (StringUtils.isEmpty(authorization)) {
                return null;
            }
        }

        if (authorization.startsWith("Bearer ")) {
            authorization = authorization.substring(7);
        }

        authorization = authorization.replaceAll(" ", "+");

        return authorization;
    }
}
