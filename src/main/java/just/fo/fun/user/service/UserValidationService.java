package just.fo.fun.user.service;

import just.fo.fun.common.validation.Validation;
import just.fo.fun.user.model.UserChangePasswordDto;
import just.fo.fun.user.model.UserLoginDto;

public interface UserValidationService extends Validation {

    void validateCreate(UserLoginDto userLoginDto);

    void validateUpdate(UserLoginDto userLoginDto);

    void validateChangePassword(UserChangePasswordDto userChangePasswordDto);

    void validateDelete(Long id);
}
