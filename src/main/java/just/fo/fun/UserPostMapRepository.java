package just.fo.fun;

import just.fo.fun.entities.UserPostVoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostMapRepository extends JpaRepository<UserPostVoteHistory, Long> {


    @Query("select UP from UserPostVoteHistory UP where UP.isDeleted = false and UP.post.id = :postId and UP.user.id = :userId")
    UserPostVoteHistory findByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);
}
