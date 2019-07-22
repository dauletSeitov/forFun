package just.fo.fun.auth.service;

import just.fo.fun.auth.model.AuthDto;
import just.fo.fun.entities.User;
import just.fo.fun.exception.MessageException;
import just.fo.fun.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    @Autowired
    private UserService userService;

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

        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(30L); //TODO put 30 to property

        if(user.getLockedTime() != null && localDateTime.isBefore(user.getLockedTime())){
            log.debug("user {} still locked to {}", user.getName(), user.getLockedTime().getMinute() - localDateTime.getMinute());
            throw new MessageException("user still locked to"  +  (user.getLockedTime().getMinute() - localDateTime.getMinute()));
        } else {
            user.setLockedTime(null);
            userService.save(user);
        }

        if (user.getIncorrectAttempt() > 3){ //TODO put 3 to property
            user.setLockedTime(LocalDateTime.now());
            user.setIncorrectAttempt(0);
            userService.save(user);

            log.debug("3 times incorrect attempt user is locked");

            throw new MessageException("user is locked!");
        }

        if(user.getPassword().equals(authDto.getPassword())) {
            user.setIncorrectAttempt(0);
            userService.save(user);
            log.debug("success!");

        } else {

            user.setIncorrectAttempt(user.getIncorrectAttempt() + 1);
            userService.save(user);

            log.debug("user not found for login: " + authDto.getLogin());
            throw new MessageException("incorrect login or password!");

        }



    }
}
