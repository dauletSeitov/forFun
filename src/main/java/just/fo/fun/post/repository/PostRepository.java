package just.fo.fun.post.repository;

import just.fo.fun.entities.Post;
import just.fo.fun.post.model.ResultHolderTwoLong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select P from Post P where P.isDeleted = false and P.id = :postId")
    Post findOneById(@Param("postId") Long postId);

    @Query("select P from Post P where P.isDeleted = false and P.created > :beginningWith and P.rating > :hotPageLevel")
    Page<Post> findHot(@Param("hotPageLevel") Long hotPageLevel, @Param("beginningWith") LocalDateTime beginningWith, Pageable pageable);

    @Query("select new just.fo.fun.post.model.ResultHolderTwoLong(sum(P.rating), count(1)) from Post P where P.isDeleted = false and P.user.id = :id")
    ResultHolderTwoLong getAggregatedDataByUser(@Param("id") Long id);

    @Query("select P from Post P where P.isDeleted = false and P.user.id = :userId and P.title ilike '%' + :searchText + '%'")
    Page<Post> findPostByUserId(@Param("userId") Long userId, @Param("searchText") String searchText, Pageable pageable);

    @Query("select C.post from Commentary C where C.isDeleted = false and C.post.isDeleted = false and C.user.id = :userId")
    Page<Post> findPostFromCommentaryByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("select U.post from UserPostVoteHistory U " +
            "where U.isDeleted = false " +
            "and U.post.isDeleted = false " +
            "and U.user.id = :userId " +
            "and (:isUpVote = true and U.isUpVoted = true or :isUpVote = false and U.isDownVoted = true)")
    Page<Post> findMyAssessments(@Param("isUpVote") Boolean isUpVote, @Param("userId") Long userId, Pageable request);

    @Query("select P from Post P where P.isDeleted = false and P.title ilike '%' + :searchText + '%'")
    Page<Post> findPostBySearchText(@Param("searchText") String searchText, Pageable request);

    @Query("update Post set isDeleted = true where id = :postId")
    void delete(@Param("postId") Long postId);
}
