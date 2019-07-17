package just.fo.fun.commentary.Controller;

import just.fo.fun.commentary.model.CommentaryDto;
import just.fo.fun.commentary.service.CommentaryService;
import just.fo.fun.entities.Commentary;
import just.fo.fun.entities.Post;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/commentary")
public class CommentaryController {

    @Autowired
    private CommentaryService commentaryService;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity insertCommentary(@Valid @RequestBody final CommentaryDto userDto) {

        Commentary commentary;
        try {
            commentary = commentaryService.save(userDto);
        }catch (Exception e){
            throw new MessageException("ffffffff" + e.getMessage());
        }
        return commentary == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(HttpStatus.OK);

    }


    @GetMapping("/my-commentaries")
    public ResponseEntity myPosts(Pageable request) {
        Page<PostDto> commentaries = postService.findPostFromCommentaryByUserId(request);
        return new ResponseEntity<>(commentaries, HttpStatus.OK);
    }


    /*
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
        User user = userService.getOne(id);

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

    @PostMapping("/change-rating")
    public ResponseEntity changeRatingPost(@NotNull final Long commentId, @NotNull final Boolean isUpVote) {

        commentaryService.changeRating(commentId, isUpVote);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
