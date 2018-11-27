package just.fo.fun.commentary.Controller;

import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.commentary.service.CommentaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/commentary")
public class CommentaryController {

    @Autowired
    private CommentaryService commentaryService;

 /*   @PostMapping
    public ResponseEntity insertUser(@Valid @RequestBody final UserLoginDto userDto) {

        if (userDto.getId() != null)
            throw new MessageException("id must be empty !");

        User user = new User();
        Utils.copyProperties(userDto, user);
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
    public ResponseEntity updateUser(@Valid @RequestBody final UserLoginDto userDto) {

        if (userDto.getId() == null)
            throw new MessageException("id must not be empty !");
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User resultUser = userService.save(user);
        return resultUser == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(resultUser, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable final Long id) {

        UserLoginDto userDto = new UserLoginDto();
        User user = userService.findOne(id);

        BeanUtils.copyProperties(user, userDto);
        return userDto == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity getUsers() {

        List<User> users = userService.findAll();

        List<UserLoginDto> resultUserDto = users.stream().map(itm -> {
            UserLoginDto userDto = new UserLoginDto();
            BeanUtils.copyProperties(itm, userDto);
            return userDto;
        }).collect(Collectors.toList());

        return resultUserDto == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(resultUserDto, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
*/
    //by post id
    @GetMapping("/post-id/{id}")
    public ResponseEntity getUser(@PathVariable final Long id) {

        List<CommentaryDto> result = commentaryService.getAll(id);
        return result == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(result, HttpStatus.OK);

    }


}
