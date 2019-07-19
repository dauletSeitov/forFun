package just.fo.fun.user.service;

import just.fo.fun.entities.User;
import just.fo.fun.exception.MessageException;
import just.fo.fun.property.servise.PropertyService;
import just.fo.fun.user.model.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserService userService;

    @Override
    public void validateCreate(UserLoginDto userLoginDto) {

        Long acceptableAge = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.ACCEPTABLE_AGE);
        String loginRegex = propertyService.getStringPropertyByCode(PropertyService.PropertyCode.LOGIN_REGEX);
        String passwordRegex = propertyService.getStringPropertyByCode(PropertyService.PropertyCode.PASSWORD_REGEX);

        if(userLoginDto == null){
            throw new MessageException("model not found!");

        } else if(userLoginDto.getId() != null){
            throw new MessageException("this person already exists!");

        } else if(StringUtils.isEmpty(userLoginDto.getLogin())){
            throw new MessageException("empty login!");

        } else if(!userLoginDto.getLogin().matches(loginRegex)){
            throw new MessageException("incorrect login!");

        } else if(StringUtils.isEmpty(userLoginDto.getName())){
            throw new MessageException("empty user name!");

        } else if(StringUtils.isEmpty(userLoginDto.getPassword())){
            throw new MessageException("empty password !");

        } else if(!userLoginDto.getPassword().matches(passwordRegex)){
            throw new MessageException("incorrect password!");

        } else if(userLoginDto.getBirthDay() == null){
            throw new MessageException("empty birthday!");

        } else if(LocalDate.now().minusYears(acceptableAge).isBefore(userLoginDto.getBirthDay())){
            throw new MessageException("you are to young to this site!");
        }

        User user = userService.findOneEntityByLogin(userLoginDto.getLogin());

        if (user != null){
            throw new MessageException("you already registered!");
        }

    }

}
