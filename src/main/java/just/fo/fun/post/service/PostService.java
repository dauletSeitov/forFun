package just.fo.fun.post.service;

import just.fo.fun.entities.Post;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.model.enums.PageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostDto> findPostFromCommentaryByUserId(Pageable request);

    Page<PostDto> findByPageType(PageType pageType, Pageable request);

    Page<PostDto> findPostBySearchText(String searchText, Pageable request);

    Page<PostDto> findPostByCategory(String category, Pageable request);

    PostDto findOne(Long id);

    Page<PostDto> findMyPosts(String searchText, Pageable request);

    Page<PostDto> findMyAssessments(Boolean isUpVote, Pageable request);

    Post save(PostDto postDto);

    void delete(Long id);

    void changeRating(Long postId, Boolean isUpVote);
}
