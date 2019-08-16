package just.fo.fun.common.vote;

import just.fo.fun.entities.User;

public interface VoteService {
    void commentChangeRating(Boolean isUpVote, User user, Long commentId);

    void postChangeRating(Boolean isUpVote, User user, Long postId);
}
