package just.fo.fun.post.controller;

import io.swagger.annotations.ApiOperation;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.model.enums.PageType;
import just.fo.fun.post.service.PostService;
import just.fo.fun.post.service.PostValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostValidationService postValidationService;

    @ApiOperation(value = "returns post by parameter pageType where pageType is [HOT, FRESH, TRENDING].")
    @GetMapping
    public ResponseEntity getPostByPageType(@RequestParam PageType pageType, Pageable request) {
        Page<PostDto> posts = postService.findByPageType(pageType, request);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @ApiOperation(value = "searches posts byt param searchText in title.")
    @GetMapping("/search/{searchText}")
    public ResponseEntity findPost(@PathVariable String searchText, Pageable request) {
        Page<PostDto> posts = postService.findPostBySearchText(searchText, request);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @ApiOperation(value = "get post by category.")
    @GetMapping("/category/{category}")
    public ResponseEntity getPostByCategory(@PathVariable String category, Pageable request) {
        Page<PostDto> posts = postService.findPostByCategory(category, request);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @ApiOperation(value = "returns one post.")
    @GetMapping("/{id}")
    public ResponseEntity getOnePost(@PathVariable final Long id) {
        final PostDto post = postService.findOne(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "searches my posts byt param searchText in title.")
    @GetMapping("/my-posts/{searchText}")
    public ResponseEntity myPosts(Pageable request, @PathVariable String searchText) {
        Page<PostDto> myPosts = postService.findMyPosts(searchText, request);
        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @ApiOperation(value = "returns my assessments.")
    @GetMapping("/my-assessments/{isUpVote}")
    public ResponseEntity myPosts(@PathVariable Boolean isUpVote, Pageable request) {
        Page<PostDto> myPosts = postService.findMyAssessments(isUpVote, request);
        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @ApiOperation(value = "creates new post.")
    @PostMapping
    public ResponseEntity createPost(@RequestBody final PostDto postDto) {

        postValidationService.validateCreate(postDto);

        postService.save(postDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
//not supported
//    @PutMapping
//    public ResponseEntity updatePost(@Valid @RequestBody final PostDto postDto) {
//
//        if (postDto.getId() == null)
//            throw new MessageException("id must not be empty !");
//
//        try {
//            postService.create(postDto);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @ApiOperation(value = "deletes post by id.")
    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable final Long id) {

        postValidationService.validateId(id);
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @ApiOperation(value = "changes rating by postId.")
    @PostMapping("/change-rating")
    public ResponseEntity changeRatingPost(@NotNull final Long postId, @NotNull final Boolean isUpVote) {

        postService.changeRating(postId, isUpVote);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
