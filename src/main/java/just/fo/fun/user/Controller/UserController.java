package just.fo.fun.user.Controller;

import io.swagger.annotations.ApiOperation;
import just.fo.fun.user.model.UserChangePasswordDto;
import just.fo.fun.user.service.UserValidationService;
import just.fo.fun.user.model.CurrentUserDto;
import just.fo.fun.user.model.UserLoginDto;
import just.fo.fun.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation(value = "to get current current user data.")
    @GetMapping("/current/user-data")
    public ResponseEntity getCurrentUserData() {

        CurrentUserDto currentUserData = userService.getCurrentUserData();

        return new ResponseEntity<>(currentUserData, HttpStatus.OK);

    }

    @ApiOperation(value = "sign up new user.")
    @PostMapping("/sign-up")
    public ResponseEntity createUser(@Valid @RequestBody final UserLoginDto userLoginDto) {

        userValidationService.validateCreate(userLoginDto);

        userService.create(userLoginDto);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @ApiOperation(value = "updates user info.")
    @PutMapping
    public ResponseEntity updateUser(@Valid @RequestBody final UserLoginDto userLoginDto) {

        userValidationService.validateUpdate(userLoginDto);

        userService.update(userLoginDto);

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @ApiOperation(value = "to change password.")
    @PostMapping("current/user-change-password")
    public ResponseEntity changeUserPassword(@Valid @RequestBody final UserChangePasswordDto userChangePasswordDto) {

        userValidationService.validateChangePassword(userChangePasswordDto);

        userService.changePassword(userChangePasswordDto);

        return new ResponseEntity<>(HttpStatus.OK);

    }
// not supported
//    @GetMapping("/{id}")
//    public ResponseEntity getUser(@PathVariable final Long id) {
//        UserDto user = userService.findOne(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//
//    }

// not supported
//    @GetMapping
//    public ResponseEntity getUsers(Pageable pageable) {
//
//        final Page<UserDto> page = userService.findAll(pageable);
//        return new ResponseEntity<>(page, HttpStatus.OK);
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Long id) {
        userValidationService.validateDelete(id);
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
