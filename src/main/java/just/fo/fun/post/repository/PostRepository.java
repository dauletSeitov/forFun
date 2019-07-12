package just.fo.fun.post.repository;

import just.fo.fun.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("update Post set isDeleted = true where id = :postId")
    void delete(@Param("postId") Long postId);

    @Query("update Post set isDeleted = true where id = :postId")
    void findBy(@Param("postId") Long postId);

    @Query("select P from Post P where P.isDeleted = false and P.created > :beginningWith and P.rating > :hotPageLevel")
    Page<Post> findHot(@Param("hotPageLevel") Long hotPageLevel, @Param("beginningWith") LocalDateTime beginningWith, Pageable pageable);
}
