package just.fo.fun;

import just.fo.fun.entities.UserPostMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostMapRepository extends JpaRepository<UserPostMap, Long> {


    @Query("select UP from UserPostMap UP where UP.deleted = false and UP.post.id = :postId and UP.user.id = :userId")
    UserPostMap findByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);
}
