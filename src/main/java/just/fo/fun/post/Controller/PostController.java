package just.fo.fun.post.Controller;

import io.swagger.annotations.ApiOperation;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.model.enums.PageType;
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


@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity getPostByCategory(@RequestParam PageType pageType,  Pageable request) {
        Page<PostDto> posts = postService.findByPageType(pageType, request);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOnePost(@PathVariable final Long id) {
        final PostDto post = postService.findOne(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/my-posts")
    public ResponseEntity myPosts(Pageable request) {
        Page<PostDto> myPosts = postService.findMyPosts(request);
        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @ApiOperation(value = "To get my assessments.", notes = "isUpVote is true or false.")
    @GetMapping("/my-assessments/{isUpVote}")
    public ResponseEntity myPosts(@PathVariable Boolean isUpVote, Pageable request) {
        Page<PostDto> myPosts = postService.findMyAssessments(isUpVote, request);
        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertPost(@Valid @RequestBody final PostDto postDto) {

        if (postDto.getId() != null)
            throw new MessageException("id must be empty !");

        try {
            postService.save(postDto);
        }catch (Exception e){
            log.error("error while post", e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updatePost(@Valid @RequestBody final PostDto postDto) {

        if (postDto.getId() == null)
            throw new MessageException("id must not be empty !");

        try {
            postService.save(postDto);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/change-rating")
    public ResponseEntity changeRatingPost(@NotNull final Long postId, @NotNull final Boolean isUpVote) {

        postService.changeRating(postId, isUpVote);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
