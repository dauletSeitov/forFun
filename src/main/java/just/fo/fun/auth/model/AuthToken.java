package just.fo.fun.auth.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

public class AuthToken extends AbstractAuthenticationToken {

    private Long userId;
    private String login;

    public AuthToken(Long userId, String login, UserType userType) {
        super(Collections.singletonList(new SimpleGrantedAuthority(userType.name())));
        this.login = login;
        this.userId = userId;
    }

    @Override
    public String getCredentials() {
        return login;
    }

    @Override
    public Long getPrincipal() {
        return userId;
    }
}
