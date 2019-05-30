package just.fo.fun.post.repository;

import just.fo.fun.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("update Post set isDeleted = true where id = :postId")
    void delete(@Param("postId") Long postId);
}
