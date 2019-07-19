package just.fo.fun.user.service;

import just.fo.fun.exception.MessageException;
import just.fo.fun.user.model.UserLoginDto;
import org.springframework.util.StringUtils;


public class UserValidationServiceImpl implements UserValidationService {

    private static final String LOGIN_REGEX = "[0-9a-zA-Z]*";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @Override
    public void validateCreate(UserLoginDto userLoginDto) {

        if(userLoginDto == null){
            throw new MessageException("model not found!");

        } else if(userLoginDto.getId() != null){
            throw new MessageException("this person already exists!");

        } else if(StringUtils.isEmpty(userLoginDto.getLogin())){
            throw new MessageException("empty login!");

        } else if(!userLoginDto.getLogin().matches(LOGIN_REGEX)){
            throw new MessageException("incorrect login!");

        } else if(StringUtils.isEmpty(userLoginDto.getName())){
            throw new MessageException("empty user name!");

        } else if(StringUtils.isEmpty(userLoginDto.getPassword())){
            throw new MessageException("empty password !");
        } else if(!userLoginDto.getPassword().matches(PASSWORD_REGEX)){
            throw new MessageException("incorrect login!");

        }

    }

}
