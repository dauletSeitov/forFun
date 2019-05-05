package just.fo.fun.auth.controller;

import just.fo.fun.auth.model.AuthDto;
import just.fo.fun.auth.model.UserType;
import just.fo.fun.auth.service.AuthService;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static just.fo.fun.constants.Constants.VALUE;


@RestController
@RequestMapping("/api/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity auth(@RequestBody AuthDto authDto){

        if(StringUtils.isEmpty(authDto.getLogin()) || StringUtils.isEmpty(authDto.getPas())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDto user = userService.getOne(authDto.getLogin());

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        String token = authService.generateToken(user.getId(), UserType.User, user.getLogin());

        return new ResponseEntity<>(Collections.singletonMap(VALUE, token), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity insertDictionary(@RequestBody final Post post) {
//
//        Post res = authService.create(post);
//
//        return res == null
//                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//                : new ResponseEntity<>(HttpStatus.CREATED);
//
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Post>> getAll() {
//        List<Post> result = authService.getAll();
//        return result == null
//                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//                : new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
