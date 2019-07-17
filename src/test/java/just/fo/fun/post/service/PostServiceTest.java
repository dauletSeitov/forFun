package just.fo.fun.post.service;

import just.fo.fun.entities.Category;
import just.fo.fun.entities.Post;
import just.fo.fun.entities.User;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

//
//    @Mock
//    private PostRepository postRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private PostService postService;
//
//    private Page<Post> page;
//
//    private User user;
//
//    private Category category;
//
//    private PostDto postDto;
//
//    private Post post;
//
//    private UserDto userDto;
//
//
//    @Before
//    public void prepare() {
//        user = new User("login", "password", "name", LocalDateTime.now());
//        user.setId(1L);
//
//        category = new Category("HELLO");
//
//        userDto = new UserDto(user);
//
//        post = new Post(user, "title", "url", LocalDateTime.now(), category, 1L);
//
//        page = new PageImpl<>(Arrays.asList(post, post));
//
//        postDto = new PostDto(post.getId(), userDto, post.getTitle(), post.getImageUrl(), LocalDateTime.now(), category.getName(), 240L);
//
//    }
//
//    @Test
//    public void findAll() {
//
//
//        Pageable pageable = new PageRequest(0, 20);
//
//        Mockito.doReturn(page).when(postRepository).findAll(pageable);
//
//        Page<PostDto> all = postService.findByPageType(pageable);
//
//        assertNotNull(all);
//        assertNotNull(all.getContent());
//        assertEquals(2, all.getContent().size());
//        assertNotNull(all.getContent().get(0).getUser());
//        assertEquals(all.getContent().get(0).getUser().getName(), user.getName());
//        assertEquals(all.getContent().get(0).getUser().getLogin(), user.getLogin());
//        assertNotNull(all.getContent().get(0).getCategory(), category.getName());
//
//    }
//
//    @Test
//    public void postDtoToPost() {
//
//
//        Mockito.doReturn(user).when(userRepository).findOne(user.getId());
//
//        Post post = postService.postDtoToPost(postDto);
//
//
//
//        assertNotNull(post);
//        assertEquals(post.getTitle(), this.post.getTitle());
//        assertEquals(post.getImageUrl(), this.post.getImageUrl());
//        assertNotNull(post.getUser());
//        assertEquals(post.getUser().getLogin(), user.getLogin());
//        assertEquals(post.getUser().getName(), user.getName());
//        assertEquals(post.getUser().getPassword(), user.getPassword());
//        assertEquals(post.getUser().getId(), user.getId());
//
//    }
}