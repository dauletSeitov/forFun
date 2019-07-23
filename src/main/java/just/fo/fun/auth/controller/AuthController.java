package just.fo.fun.auth.controller;

import just.fo.fun.auth.model.AuthDto;
import just.fo.fun.auth.model.UserType;
import just.fo.fun.auth.service.AuthService;
import just.fo.fun.auth.service.AuthValidationService;
import just.fo.fun.entities.User;
import just.fo.fun.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static just.fo.fun.constants.Constants.MESSAGE;
import static just.fo.fun.constants.Constants.VALUE;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthValidationService authValidationService;

    @PostMapping("/login")
    public ResponseEntity auth(@RequestBody AuthDto authDto){

        authValidationService.validateAuth(authDto);

        User user = userService.findOneEntityByLogin(authDto.getLogin());

        String token = authService.generateToken(user.getId(), UserType.User, user.getLogin());

        return new ResponseEntity<>(Collections.singletonMap(VALUE, token), HttpStatus.OK);
    }



}
