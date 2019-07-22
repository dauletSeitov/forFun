package just.fo.fun.auth.service;

import just.fo.fun.auth.model.AuthDto;
import just.fo.fun.entities.User;
import just.fo.fun.exception.MessageException;
import just.fo.fun.property.servise.PropertyService;
import just.fo.fun.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    private Long userLockTime;
    private Long userIncorrectAttempt;

    @PostConstruct
    private void init(){
        userLockTime = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.USER_LOCK_TIME);
        userIncorrectAttempt = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.USER_INCORRECT_ATTEMPT);
    }

    @Override
    public void validateAuth(AuthDto authDto) {

        if(StringUtils.isEmpty(authDto.getLogin()) || StringUtils.isEmpty(authDto.getPassword())) {
            log.debug("login or password can not be empty!" + authDto);
            throw new MessageException("incorrect login or password!");
        }

        User user = userService.findOneEntityByLogin(authDto.getLogin());

        if(user == null) {
            log.debug("user not found for login: " + authDto.getLogin());
            throw new MessageException("incorrect login or password!");
        }


        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(userLockTime);

        if(user.getLockedTime() != null && localDateTime.isBefore(user.getLockedTime())){
            log.debug("user {} is locked until {}", user.getName(), user.getLockedTime().plusMinutes(userLockTime));
            throw new MessageException("user is locked until "  +  user.getLockedTime().plusMinutes(userLockTime));
        } else {
            user.setLockedTime(null);
            userService.save(user);
        }

        if (user.getIncorrectAttempt() > userIncorrectAttempt - 2){
            user.setLockedTime(LocalDateTime.now());
            user.setIncorrectAttempt(0);
            userService.save(user);

            log.debug("3 times incorrect attempt user is locked");

            throw new MessageException("user is locked until "  +  user.getLockedTime().plusMinutes(userLockTime));
        }

        if(user.getPassword().equals(authDto.getPassword())) {
            user.setIncorrectAttempt(0);
            userService.save(user);
            log.debug("success!");

        } else {

            user.setIncorrectAttempt(user.getIncorrectAttempt() + 1);
            userService.save(user);

            log.debug("incorrect login or password!");
            throw new MessageException("incorrect login or password!");

        }



    }
}
