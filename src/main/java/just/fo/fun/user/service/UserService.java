package just.fo.fun.user.service;

import just.fo.fun.entities.User;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public User save(User user){
        return UserRepository.save(user);
    }

    public UserDto findOne(Long id){
        final User user = UserRepository.findOne(id);
        return Objects.isNull(user) ? null : new UserDto(user);
    }

    public Page<UserDto> findAll(Pageable pageable){
        final Page<User> page = UserRepository.findAll(pageable);
        return page.map(UserDto::new);
    }

    public void delete(Long id){
        UserRepository.delete(id);
    }


}
