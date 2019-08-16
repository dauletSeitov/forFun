package just.fo.fun.auth.service;

import just.fo.fun.auth.model.UserType;

public interface AuthService {
    String generateToken(Long id, UserType user, String login);

    void authenticate(String token);
}
