package just.fo.fun.post.service;

import just.fo.fun.category.repository.CategoryRepository;
import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.common.vote.VoteService;
import just.fo.fun.entities.Category;
import just.fo.fun.entities.Post;
import just.fo.fun.entities.UserPostVoteHistory;
import just.fo.fun.exception.MessageException;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.model.enums.PageType;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.post.userPostVoteHistory.UserPostVoteHistoryRepository;
import just.fo.fun.property.servise.PropertyService;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Objects;

@Transactional
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private VoteService voteService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private UserPostVoteHistoryRepository userPostVoteHistoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Long hotPageLevel;

    private Long hotPageDays;

    @PostConstruct
    private void init(){

        hotPageLevel = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.HOT_PAGE_LEVEL);
        hotPageDays = propertyService.getLongPropertyByCode(PropertyService.PropertyCode.HOT_PAGE_DAYS);

    }

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

                page = postRepository.findAllHotByLevelAndTimeNotDeleted(hotPageLevel, LocalDateTime.now().minusDays(hotPageDays), pageable);

        }

        return page.map(this::postDtoToPost);
    }

    public PostDto findOne(Long id){
        return postDtoToPost(postRepository.findOne(id));
    }

    public Post save(PostDto postDto){
        postDto.setRating(0L);
        return postRepository.save(postDtoToPost(postDto));
    }

    public void delete(Long postId){

        Post post = postRepository.findOne(postId);

        Objects.requireNonNull(postId, "post not found!");

        if (post.getUser().getId().equals(requestUtils.getUser().getId())){
            postRepository.delete(postId);

        } else {

            throw new MessageException("this is not your post!");
        }
    }

    public void changeRating(Long postId, Boolean isUpVote) {
        voteService.postChangeRating(isUpVote, requestUtils.getUser(), postId);
    }


    public Page<PostDto> findMyPosts(String searchText, Pageable request) {
        return postRepository.findAllPostByUserIdNotDeleted(requestUtils.getUser().getId(), searchText, request).map(this::postDtoToPost);
    }

    public Page<PostDto> findPostFromCommentaryByUserId(Pageable request) {
        return postRepository.findAllPostFromCommentaryByUserIdNotDeleted(requestUtils.getUser().getId(), request).map(this::postDtoToPost);
    }

    public Page<PostDto> findMyAssessments(Boolean isUpVote, Pageable request) {
        Objects.requireNonNull(isUpVote, "required param isUpVote");
        return postRepository.findAllMyAssessmentsByUserIdNotDeleted(isUpVote,requestUtils.getUser().getId(), request).map(this::postDtoToPost);
    }

    public Page<PostDto> findPostBySearchText(String searchText, Pageable request) {
        return postRepository.findAllPostBySearchTextNotDeleted(searchText, request).map(this::postDtoToPost);
    }

    public Page<PostDto> findPostByCategory(String category, Pageable request) {
        return postRepository.findAllPostByCategoryNotDeleted(category, request).map(this::postDtoToPost);
    }


    //-------------------CONVERTER----------------------------
    public Post postDtoToPost(PostDto postDto) {

        final Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setImageUrl(postDto.getImageUrl());
        post.setRating(postDto.getRating());

        Category category = categoryRepository.findOneNotDeletedByNameNotDeleted(postDto.getCategory());
        post.setCategory(category);

        post.setUser(requestUtils.getUser());

        return post;
    }


    public PostDto postDtoToPost(Post post) {

        Long commentaryCount = commentaryRepository.getCommentaryCountByPostIdNotDeleted(post.getId());
        UserPostVoteHistory userPostVoteHistory = userPostVoteHistoryRepository.findOneByUserIdAndPostIdNotDeleted(requestUtils.getUser().getId(), post.getId());

        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setImageUrl(post.getImageUrl());
        postDto.setUpdated(post.getUpdated());
        postDto.setCategory(post.getCategory().getName());
        postDto.setRating(post.getRating());
        postDto.setUser(new UserDto(post.getUser()));
        postDto.setIsUpVoted(userPostVoteHistory != null && userPostVoteHistory.getIsUpVoted() != null && userPostVoteHistory.getIsUpVoted());
        postDto.setCommentCount(commentaryCount);
        return postDto;
    }


    //-------------------CONVERTER----------------------------
}
