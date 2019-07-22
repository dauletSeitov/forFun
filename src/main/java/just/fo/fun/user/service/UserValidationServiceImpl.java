package just.fo.fun.user.service;

import just.fo.fun.entities.User;
import just.fo.fun.exception.MessageException;
import just.fo.fun.property.servise.PropertyService;
import just.fo.fun.user.model.UserChangePasswordDto;
import just.fo.fun.user.model.UserLoginDto;
import just.fo.fun.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestUtils requestUtils;

    private Long acceptableAge;
    private String loginRegex;
    private String passwordRegex;

    @PostConstruct
    private void init(){

        acceptableAge = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.ACCEPTABLE_AGE);
        loginRegex = propertyService.getStringPropertyByCode(PropertyService.PropertyCode.LOGIN_REGEX);
        passwordRegex = propertyService.getStringPropertyByCode(PropertyService.PropertyCode.PASSWORD_REGEX);

    }


    @Override
    public void validateCreate(UserLoginDto userLoginDto) {

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

        } else if(StringUtils.isEmpty(userLoginDto.getEmail()) && !StringUtils.isEmpty(userLoginDto.getPhone())){
            if (!userLoginDto.getPhone().matches("[0-9]*") || userLoginDto.getPhone().length() != 10){
                throw new MessageException("incorrect phone!");
            }
        } if(!StringUtils.isEmpty(userLoginDto.getEmail()) && StringUtils.isEmpty(userLoginDto.getPhone())){
            if (!isValidEmail(userLoginDto.getEmail())){
                throw new MessageException("incorrect email!");
            }
        } if(StringUtils.isEmpty(userLoginDto.getEmail()) && StringUtils.isEmpty(userLoginDto.getPhone())){
            throw new MessageException("phone or email cannot be empty!");
        }

        User user = userService.findOneEntityByLogin(userLoginDto.getLogin());

        if (user != null){
            throw new MessageException("you already registered!");
        }

    }

    @Override
    public void validateUpdate(UserLoginDto userLoginDto) {

        if(userLoginDto == null){
            throw new MessageException("model not found!");

        } else if(userLoginDto.getId() == null){
            throw new MessageException("incorrect identifier!");

        } else if(!userLoginDto.getId().equals(requestUtils.getUser().getId())){
            throw new MessageException("you can not change other users parameters!");

        } else if(StringUtils.isEmpty(userLoginDto.getLogin())){
            throw new MessageException("empty login!");

        } else if(!userLoginDto.getLogin().matches(loginRegex)){
            throw new MessageException("incorrect login!");

        } else if(StringUtils.isEmpty(userLoginDto.getName())){
            throw new MessageException("empty user name!");

        } else /*if(StringUtils.isEmpty(userLoginDto.getPassword())){
            throw new MessageException("empty password !");

        } else if(!userLoginDto.getPassword().matches(passwordRegex)){
            throw new MessageException("incorrect password!");

        } else*/ if(userLoginDto.getBirthDay() == null){
            throw new MessageException("empty birthday!");

        } else if(LocalDate.now().minusYears(acceptableAge).isBefore(userLoginDto.getBirthDay())){
            throw new MessageException("you are to young to this site!");

        } else if(StringUtils.isEmpty(userLoginDto.getEmail()) && !StringUtils.isEmpty(userLoginDto.getPhone())){
            if (!userLoginDto.getPhone().matches("[0-9]*") || userLoginDto.getPhone().length() != 10){
                throw new MessageException("incorrect phone!");
            }
        } if(!StringUtils.isEmpty(userLoginDto.getEmail()) && StringUtils.isEmpty(userLoginDto.getPhone())){
            if (!isValidEmail(userLoginDto.getEmail())){
                throw new MessageException("incorrect email!");
            }
        } if(StringUtils.isEmpty(userLoginDto.getEmail()) && StringUtils.isEmpty(userLoginDto.getPhone())){
            throw new MessageException("phone or email cannot be empty!");
        }

    }

    @Override
    public void validateChangePassword(UserChangePasswordDto userChangePasswordDto) {

        if (StringUtils.isEmpty(userChangePasswordDto.getNewPassword())){
            throw new MessageException("new password can not be empty!");
        } else if (StringUtils.isEmpty(userChangePasswordDto.getOldPassword())){
            throw new MessageException("old password can not be empty!");
        }


    }

    public static boolean isValidEmail(String email){

        if (StringUtils.isEmpty(email)){
            return false;
        }

        if (!email.matches("[0-9a-zA-Z!@#$%^&*_+\\.]*")){
            return false;
        }

        if(!(email.contains(".") || email.contains("@"))){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("88".matches("[0-9]*"));
    }


}
