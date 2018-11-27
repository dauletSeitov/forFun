package just.fo.fun.post.service;

import just.fo.fun.entities.Post;
import just.fo.fun.entities.User;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<PostDto> findAll(Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        return page.map(PostDto::new);
    }

    public PostDto findOne(Long id){
        return new PostDto(postRepository.findOne(id));
    }

    public Post save(PostDto postDto){
        return postRepository.save(PostDtoToPost(postDto));
    }

    public void delete(Long id){
        postRepository.delete(id);
    }


    //-------------------CONVERTER----------------------------
    public Post PostDtoToPost(PostDto postDto) {
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
