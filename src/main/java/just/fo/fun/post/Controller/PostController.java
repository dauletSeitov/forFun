package just.fo.fun.post.Controller;

import just.fo.fun.exception.MessageException;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.service.PostService;
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
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity getAllPost(Pageable request) {
        Page<PostDto> posts = postService.findAll(request);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOnePost(@PathVariable final Long id) {
        final PostDto post = postService.findOne(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertPost(@Valid @RequestBody final PostDto postDto) {

        if (postDto.getId() != null)
            throw new MessageException("id must be empty !");

        try {
            postService.save(postDto);
        }catch (Exception e){
            log.error("erron while post", e);
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

}
