package just.fo.fun.user.Controller;

import io.swagger.annotations.ApiOperation;
import just.fo.fun.user.model.UserChangePasswordDto;
import just.fo.fun.user.service.UserValidationService;
import just.fo.fun.exception.MessageException;
import just.fo.fun.user.model.CurrentUserDto;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.user.model.UserLoginDto;
import just.fo.fun.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidationService userValidationService;


    @GetMapping("/current/user-data")
    @ApiOperation(value = "to get current current user data.")
    public ResponseEntity getCurrentUserData() {

        CurrentUserDto currentUserData = userService.getCurrentUserData();
        return currentUserData == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(currentUserData, HttpStatus.OK);

    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "sign up new user.")
    public ResponseEntity createUser(@Valid @RequestBody final UserLoginDto userLoginDto) {

        userValidationService.validateCreate(userLoginDto);

        try {
            userService.save(userLoginDto);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new MessageException("could not create the user!");
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping
    @ApiOperation(value = "updates user info.")
    public ResponseEntity updateUser(@Valid @RequestBody final UserLoginDto userLoginDto) {

        userValidationService.validateUpdate(userLoginDto);

        try {
            userService.update(userLoginDto);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new MessageException("could not create the user!");
        }

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping("current/user-change-password")
    @ApiOperation(value = "to change password.")
    public ResponseEntity changeUserPassword(@Valid @RequestBody final UserChangePasswordDto userChangePasswordDto) {

        userValidationService.validateChangePassword(userChangePasswordDto);

        userService.changePassword(userChangePasswordDto);

        return new ResponseEntity<>(HttpStatus.OK);

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
