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

    @Query("select P from Post P " +
            "where P.isDeleted = false " +
            "and P.user.isDeleted = false " +
            "and P.id = :postId")
    Post findOneByIdNotDeleted(@Param("postId") Long postId);


    @Query("select P from Post P " +
            "where P.isDeleted = false " +
            "and P.user.isDeleted = false " +
            "and P.created > :beginningWith " +
            "and P.rating > :hotPageLevel")
    Page<Post> findAllHotByLevelAndTimeNotDeleted(@Param("hotPageLevel") Long hotPageLevel, @Param("beginningWith") LocalDateTime beginningWith, Pageable pageable);


    @Query("select new just.fo.fun.post.model.ResultHolderTwoLong(sum(P.rating), sum (1)) from Post P " +
            "where P.isDeleted = false " +
            "and P.user.isDeleted = false " +
            "and P.user.id = :id")
    ResultHolderTwoLong getAggregatedDataByUserNotDeleted(@Param("id") Long id);


    @Query("select P from Post P " +
            "where P.isDeleted = false " +
            "and P.user.isDeleted = false " +
            "and P.user.id = :userId " +
            "and UPPER(P.title) like CONCAT('%', UPPER(:searchText), '%')")
    Page<Post> findAllPostByUserIdNotDeleted(@Param("userId") Long userId, @Param("searchText") String searchText, Pageable pageable);


    @Query("select C.post from Commentary C " +
            "where C.isDeleted = false " +
            "and C.post.isDeleted = false " +
            "and C.user.isDeleted = false " +
            "and C.user.id = :userId")
    Page<Post> findAllPostFromCommentaryByUserIdNotDeleted(@Param("userId") Long userId, Pageable pageable);


    @Query("select U.post from UserPostVoteHistory U " +
            "where U.isDeleted = false " +
            "and U.post.isDeleted = false " +
            "and U.post.user.isDeleted = false " +
            "and U.user.isDeleted = false " +
            "and U.user.id = :userId " +
            "and (:isUpVote = true and U.isUpVoted = true or :isUpVote = false and U.isDownVoted = true)")
    Page<Post> findAllMyAssessmentsByUserIdNotDeleted(@Param("isUpVote") Boolean isUpVote, @Param("userId") Long userId, Pageable request);


    @Query("select P from Post P " +
            "where P.isDeleted = false " +
            "and P.user.isDeleted = false " +
            "and UPPER(P.title) like CONCAT('%', UPPER(:searchText), '%')")
    Page<Post> findAllPostBySearchTextNotDeleted(@Param("searchText") String searchText, Pageable request);


    @Query("select P from Post P " +
            "where P.isDeleted = false " +
            "and P.user.isDeleted = false " +
            "and P.category.name = :category")
    Page<Post> findAllPostByCategoryNotDeleted(@Param("category") String category, Pageable request);


    @Query("select P from Post P " +
            "where P.isDeleted = false " +
            "and P.user.isDeleted = false " +
            "and P.category.id = :categoryId")
    Page<Post> findAllPostByCategoryNotDeleted(@Param("categoryId") Integer categoryId, Pageable request);


    @Query("update Post set isDeleted = true where id = :postId")
    void delete(@Param("postId") Long postId);

    @Query("update Post P set P.isDeleted = true where P.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
