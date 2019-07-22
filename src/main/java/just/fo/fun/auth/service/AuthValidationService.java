package just.fo.fun.auth.service;

import just.fo.fun.auth.model.AuthDto;

public interface AuthValidationService {

    void validateAuth(AuthDto authDto);

}
