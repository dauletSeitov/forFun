package just.fo.fun.post.service;

import just.fo.fun.UserPostMapRepository;
import just.fo.fun.entities.Post;
import just.fo.fun.entities.User;
import just.fo.fun.entities.UserPostMap;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.repository.DSLPostRepository;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.user.repository.UserRepository;
import just.fo.fun.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private UserPostMapRepository userPostMapRepository;

    public Page<PostDto> findAll(Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        return page.map(PostDto::new);
    }

    public PostDto findOne(Long id){
        return new PostDto(postRepository.findOne(id));
    }

    public Post save(PostDto postDto){
        return postRepository.save(postDtoToPost(postDto));
    }

    public void delete(Long id){

        Objects.requireNonNull(id, "id can not be null");
        Post post = postRepository.findOne(id);

        if (post != null && post.getUser().getId().equals(requestUtils.getUser().getId())){
            postRepository.delete(id);
        } else throw new MessageException("this is not your post!");
    }

    public void changeRating(Long postId, Boolean isUpVote) {

        Objects.requireNonNull(postId, "id can not be null!");
        Objects.requireNonNull(postId, "isUpVote can not be null!");

        User user = requestUtils.getUser();
        UserPostMap userPostMap = userPostMapRepository.findByUserAndPost(user.getId(), postId);

        Post post = postRepository.findOne(postId);
        Objects.requireNonNull(post, "there is no post with id " + postId);

        Long rating = post.getRating();

        if (userPostMap == null) {

            if (isUpVote) {
                rating++;
            } else {
                rating--;
            }
        } else if (isUpVote && userPostMap.getIsUpVote()) {
            rating--;
        } else if (!isUpVote && !userPostMap.getIsUpVote()) {
            rating++;
        } else if (!isUpVote && userPostMap.getIsUpVote()) {
            rating -= 2;
        } else if (isUpVote && !userPostMap.getIsUpVote()) {
            rating += 2;
        }

        System.out.println("rating = " + rating);
        System.out.println("post = " + post.getRating());




        if (userPostMap == null){
            userPostMap = new UserPostMap();
            userPostMap.setIsUpVote(isUpVote);
            userPostMap.setPost(post);
            userPostMap.setUser(user);
        } else {
            userPostMap.setIsUpVote(isUpVote);
        }


        userPostMapRepository.save(userPostMap);

    }

    //-------------------CONVERTER----------------------------
    public Post postDtoToPost(PostDto postDto) {
        final Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setImageUrl(postDto.getImageUrl());
        post.setCategory(postDto.getCategory());

        final User user = userRepository.findOne(postDto.getUser().getId());
        post.setUser(user);
        return post;
    }


    //-------------------CONVERTER----------------------------
}
