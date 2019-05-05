package just.fo.fun.auth.service;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import just.fo.fun.auth.model.AuthToken;
import just.fo.fun.auth.model.UserType;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.audience}")
    private String audience;

    @Value("${jwt.issuer}")
    private String issuer;

    public void authenticate(String token) {

        if (token == null) {
            return;
        }

        AuthToken authToken = checkToken(token);

        if(authToken.getPrincipal() == null || authToken.getCredentials() == null) {
            return;
        }

        UserDto user = userService.getOne(authToken.getPrincipal());

        if (user == null) { //TODO add is blocked is deleted
            authToken.setAuthenticated(false);
        } else {
            authToken.setAuthenticated(true);
        }

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }


    private AuthToken checkToken(String token) throws AuthenticationException {

        try{
            
            JWTVerifier verifier = new JWTVerifier(secret, audience, issuer);

            Map<String, Object> claims = verifier.verify(token);

            return new AuthToken(
                    claims.get("userId") == null ? null : Long.parseLong((String) claims.get("userId")),
                    claims.get("login") == null ? null : (String) claims.get("login"),
                    claims.get("type") == null ? null : UserType.valueOf((String) claims.get("type")));

            
        } catch (JWTVerifyException | NoSuchAlgorithmException | IOException | InvalidKeyException | SignatureException e) {
            throw new AuthenticationServiceException("Unable to auth", e);
        }
    }


    public String generateToken(Long userId, UserType type, String login) {

        Long iat = System.currentTimeMillis();
        JWTSigner signer = new JWTSigner(secret);
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", issuer);
        claims.put("aud", audience);
        claims.put("iat", iat);
        claims.put("userId", "" + userId);
        claims.put("jit", UUID.randomUUID().toString());
        claims.put("type", type == null ? null : type.name());
        claims.put("login", login);

        return signer.sign(claims);
    }
}
