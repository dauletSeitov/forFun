package just.fo.fun.user.service;

import just.fo.fun.user.model.UserLoginDto;
import org.springframework.stereotype.Service;

@Service
public interface UserValidationService {

    void validateCreate(UserLoginDto userLoginDto);
}
