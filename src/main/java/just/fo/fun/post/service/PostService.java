package just.fo.fun.post.service;

import just.fo.fun.common.VoteService;
import just.fo.fun.post.userPostVoteHistory.UserPostVoteHistoryRepository;
import just.fo.fun.entities.Post;
import just.fo.fun.entities.User;
import just.fo.fun.entities.UserPostVoteHistory;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.model.enums.PageType;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.property.servise.PropertyService;
import just.fo.fun.user.repository.UserRepository;
import just.fo.fun.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private VoteService voteService;

    @Autowired
    private PropertyService propertyService;

    public Page<PostDto> findByPageType(PageType pageType, Pageable pageable) {

        Page<Post> page;

        switch (pageType){
            case TRENDING:
                page = postRepository.findAll(pageable); //TODO add algorithm to trending
                break;
            case FRESH:
                PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "created");
                page = postRepository.findAll(pageRequest);
                break;
            default:

                Long hotPageLevel = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.HOT_PAGE_LEVEL);
                Long hotPageDays = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.HOT_PAGE_DAYS);

                page = postRepository.findHot(hotPageLevel, LocalDateTime.now().minusDays(hotPageDays), pageable);

        }

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
        voteService.postChangeRating(isUpVote, requestUtils.getUser(), postId);
    }


    public Page<PostDto> findMyPosts(Pageable request) {
        return postRepository.findPostByUserId(requestUtils.getUser().getId(), request).map(PostDto::new);
    }

    public Page<PostDto> findPostFromCommentaryByUserId(Pageable request) {
        return postRepository.findPostFromCommentaryByUserId(requestUtils.getUser().getId(), request).map(PostDto::new);
    }

    public Page<PostDto> findMyAssessments(Boolean isUpVote, Pageable request) {
        Objects.requireNonNull(isUpVote, "required param isUpVote");
        return postRepository.findMyAssessments(isUpVote,requestUtils.getUser().getId(), request).map(PostDto::new);
    }


    //-------------------CONVERTER----------------------------
    public Post postDtoToPost(PostDto postDto) {
        final Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setImageUrl(postDto.getImageUrl());
        //post.setCategory(postDto.getCategory());

        final User user = userRepository.findOne(postDto.getUser().getId());
        post.setUser(user);
        return post;
    }




    //-------------------CONVERTER----------------------------
}
