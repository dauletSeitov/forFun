package just.fo.fun.commentary.repository;

import just.fo.fun.entities.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    List<Commentary> getAllByParentIsNullAndPostIdOrderByRatingDesc(Long id);
    List<Commentary> getAllByParentIdOrderByRatingDesc(Long id);

    @Query("update  Commentary set isDeleted = true where id = :commentaryId")
    void delete(@Param("commentaryId") Long commentaryId);


}
