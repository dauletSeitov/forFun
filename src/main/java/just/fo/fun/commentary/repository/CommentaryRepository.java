package just.fo.fun.commentary.repository;

import just.fo.fun.entities.Commentary;
import just.fo.fun.entities.Post;
import just.fo.fun.post.model.PostDto;
import just.fo.fun.post.model.ResultHolderTwoLong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    List<Commentary> getAllByParentIsNullAndPostIdOrderByRatingDesc(Long id);
    List<Commentary> getAllByParentIdOrderByRatingDesc(Long id);

    @Query("select C from Commentary C where C.isDeleted = false and C.id = :commentId")
    Commentary findOneById(@Param("commentId") Long commentId);

    @Query("update  Commentary set isDeleted = true where id = :commentaryId")
    void delete(@Param("commentaryId") Long commentaryId);

    @Query("select new just.fo.fun.post.model.ResultHolderTwoLong(sum(P.rating), count(1)) from Commentary P where P.isDeleted = false and P.user.id = :id")
    ResultHolderTwoLong getAggregatedDataByUser(@Param("id") Long id);


}
