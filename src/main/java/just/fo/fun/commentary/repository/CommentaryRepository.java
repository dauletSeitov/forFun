package just.fo.fun.commentary.repository;

import just.fo.fun.entities.Commentary;
import just.fo.fun.post.model.ResultHolderTwoLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    @Query("select C from Commentary C " +
            "where C.isDeleted = false " +
            "and C.user.isDeleted = false " +
            "and C.post.isDeleted = false " +
            "and C.post.user.isDeleted = false " +
            "and C.parent.id is null " +
            "and C.post.id = :postId " +
            "order by C.rating desc")
    List<Commentary> findAllParentlessByPostIdNotDeleted(@Param("postId") Long postId);

    @Query("select C from Commentary C " +
            "where C.isDeleted = false " +
            "and C.user.isDeleted = false " +
            "and C.post.isDeleted = false " +
            "and C.post.user.isDeleted = false " +
            "and C.parent.id = :parentId " +
            "order by C.rating desc")
    List<Commentary> findAllByParentIdNotDeleted(@Param("parentId") Long parentId);

    @Query("select C from Commentary C where C.isDeleted = false and C.id = :commentId")
    Commentary findOneByIdNotDeleted(@Param("commentId") Long commentId);

    @Query("select new just.fo.fun.post.model.ResultHolderTwoLong(sum(C.rating), sum(1)) from Commentary C " +
            "where C.isDeleted = false " +
            "and C.post.isDeleted = false " +
            "and C.user.isDeleted = false " +
            "and C.user.id = :id ")
    ResultHolderTwoLong getAggregatedDataByUserNotDeleted(@Param("id") Long id);

    @Query("select sum(1) from Commentary C " +
            "where C.isDeleted = false " +
            "and C.post.isDeleted = false " +
            "and C.user.isDeleted = false " +
            "and C.post.id = :postId")
    Long getCommentaryCountByPostIdNotDeleted(@Param("postId") Long postId);

    @Query("update  Commentary set isDeleted = true where id = :commentaryId")
    void delete(@Param("commentaryId") Long commentaryId);

    @Query("update  Commentary C set C.isDeleted = true where C.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
