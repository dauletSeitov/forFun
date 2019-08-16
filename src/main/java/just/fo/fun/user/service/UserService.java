package just.fo.fun.user.service;

import just.fo.fun.entities.User;
import just.fo.fun.user.model.CurrentUserDto;
import just.fo.fun.user.model.UserChangePasswordDto;
import just.fo.fun.user.model.UserLoginDto;

public interface UserService {
    User findOneEntityByLogin(String login);

    User findOneEntity(Long principal);

    User create(User user);

    User create(UserLoginDto userLoginDto);

    CurrentUserDto getCurrentUserData();

    User update(UserLoginDto userLoginDto);

    void changePassword(UserChangePasswordDto userChangePasswordDto);

    void delete(Long id);
}
