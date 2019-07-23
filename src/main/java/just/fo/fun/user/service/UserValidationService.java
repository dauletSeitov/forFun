package just.fo.fun.user.service;

import just.fo.fun.user.model.UserChangePasswordDto;
import just.fo.fun.user.model.UserLoginDto;

public interface UserValidationService {

    void validateCreate(UserLoginDto userLoginDto);

    void validateUpdate(UserLoginDto userLoginDto);

    void validateChangePassword(UserChangePasswordDto userChangePasswordDto);
}
