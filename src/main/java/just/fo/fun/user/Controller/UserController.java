package just.fo.fun.user.Controller;

import just.fo.fun.entities.User;
import just.fo.fun.exception.MessageException;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.user.model.UserLoginDto;
import just.fo.fun.user.service.UserService;
import just.fo.fun.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
        UserDto user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity getUsers(Pageable pageable) {

        final Page<UserDto> page = userService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
