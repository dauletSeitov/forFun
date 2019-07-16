package just.fo.fun.post.userPostVoteHistory;

import just.fo.fun.entities.UserPostVoteHistory;
import just.fo.fun.post.model.ResultHolderTwoLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostVoteHistoryRepository extends JpaRepository<UserPostVoteHistory, Long> {


    @Query("select UP from UserPostVoteHistory UP where UP.isDeleted = false and UP.post.id = :postId and UP.user.id = :userId")
    UserPostVoteHistory findByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("select new just.fo.fun.post.model.ResultHolderTwoLong(count(CASE WHEN UP.isUpVoted = true THEN 1 END), count(CASE WHEN UP.isDownVoted = true THEN 1 END)) " +
            "from UserPostVoteHistory UP " +
            "where UP.isDeleted = false " +
            "and UP.user.id = :id ")
    ResultHolderTwoLong getAggregatedDataByUser(@Param("id") Long userId);
}
