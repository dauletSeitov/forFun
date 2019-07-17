package just.fo.fun.commentary.userCommentaryVoteHistory;

import just.fo.fun.entities.UserCommentaryVoteHistory;
import just.fo.fun.post.model.ResultHolderTwoLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentaryVoteHistoryRepository extends JpaRepository<UserCommentaryVoteHistory, Long> {


    @Query("select UP from UserCommentaryVoteHistory UP where UP.isDeleted = false and UP.commentary.id = :commentaryId and UP.user.id = :userId")
    UserCommentaryVoteHistory findByUserAndPost(@Param("userId") Long userId, @Param("commentaryId") Long commentaryId);

    @Query("select new just.fo.fun.post.model.ResultHolderTwoLong(count(CASE WHEN UP.isUpVoted = true THEN 1 END), count(CASE WHEN UP.isDownVoted = true THEN 1 END)) " +
            "from UserCommentaryVoteHistory UP " +
            "where UP.isDeleted = false " +
            "and UP.user.id = :id ")
    ResultHolderTwoLong getAggregatedDataByUser(@Param("id") Long userId);
}
