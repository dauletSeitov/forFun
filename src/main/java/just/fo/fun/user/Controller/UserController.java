package just.fo.fun.user.Controller;

import just.fo.fun.entities.User;
import just.fo.fun.exception.MessageException;
import just.fo.fun.user.model.UserLoginDto;
import just.fo.fun.user.service.UserService;
import just.fo.fun.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity insertUser(@Valid @RequestBody final UserLoginDto userLoginDto) {

        if (userLoginDto.getId() != null)
            throw new MessageException("id must be empty !");

        User user = new User();
        Utils.copyProperties(userLoginDto, user);
        User resultUser = null;
        try {
            resultUser = userService.save(user);
        }catch (Exception e){
            throw new MessageException("ffffffff" + e.getMessage());
        }
        return resultUser == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(Utils.copyProperties(resultUser, new UserLoginDto()), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity updateUser(@Valid @RequestBody final UserLoginDto userLoginDto) {

        if (userLoginDto.getId() == null)
            throw new MessageException("id must not be empty !");
        User user = new User();
        BeanUtils.copyProperties(userLoginDto, user);
        User resultUser = userService.save(user);
        return resultUser == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(resultUser, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable final Long id) {

        UserLoginDto userLoginDto = new UserLoginDto();
        User user = userService.findOne(id);

        BeanUtils.copyProperties(user, userLoginDto);
        return userLoginDto == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(userLoginDto, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity getUsers() {

        List<User> users = userService.findAll();

        List<UserLoginDto> resultUserLoginDto = users.stream().map(itm -> {
            UserLoginDto userLoginDto = new UserLoginDto();
            BeanUtils.copyProperties(itm, userLoginDto);
            return userLoginDto;
        }).collect(Collectors.toList());

        return resultUserLoginDto == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(resultUserLoginDto, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
