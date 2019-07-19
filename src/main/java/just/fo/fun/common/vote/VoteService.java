package just.fo.fun.common.vote;

import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.commentary.userCommentaryVoteHistory.UserCommentaryVoteHistoryRepository;
import just.fo.fun.entities.*;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.post.userPostVoteHistory.UserPostVoteHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class VoteService {

    @Autowired
    private UserPostVoteHistoryRepository userPostVoteHistoryRepository;

    @Autowired
    private UserCommentaryVoteHistoryRepository userCommentaryVoteHistoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;



    private Long changeRating(VoteData voteData) {

        Long rating = 0L;
        boolean isUpVoted;
        boolean isUpDownVoted;


        isUpVoted = voteData.getDbIsUpVoted();
        isUpDownVoted = voteData.getDbIsDownVoted();

        if (voteData.getUserActionIsUpVote() && voteData.getDbIsUpVoted()) {   //if user up votes up voted post
            rating--;
            isUpVoted = false;
            isUpDownVoted = false;
        } else if (voteData.getUserActionIsUpVote() && voteData.getDbIsDownVoted()) {  //if user up votes down voted post
            rating += 2;
            isUpVoted = true;
            isUpDownVoted = false;
        } else if (!voteData.getUserActionIsUpVote() && voteData.getDbIsUpVoted()) {   //if user down votes up voted post
            rating -= 2;
            isUpVoted = false;
            isUpDownVoted = true;
        } else if (!voteData.getUserActionIsUpVote() && voteData.getDbIsDownVoted()) { //if user down votes down voted post
            rating++;
            isUpVoted = false;
            isUpDownVoted = false;
        } else if (voteData.getUserActionIsUpVote() && !voteData.getDbIsUpVoted() && !voteData.getDbIsDownVoted()) {  //if user up votes not voted post
            rating++;
            isUpVoted = true;
            isUpDownVoted = false;
        } else if (!voteData.getUserActionIsUpVote() && !voteData.getDbIsUpVoted() && !voteData.getDbIsDownVoted()) { //if user down votes not voted post
            rating--;
            isUpVoted = false;
            isUpDownVoted = true;
        }


        voteData.setDbIsUpVoted(isUpVoted);
        voteData.setDbIsDownVoted(isUpDownVoted);

        return rating;
    }

    public void postChangeRating(Boolean isUpVote, User user, Long postId){

        Objects.requireNonNull(user, "user cant be empty");
        Objects.requireNonNull(postId, "post cant be empty");

        UserPostVoteHistory userPostVoteHistory = userPostVoteHistoryRepository.findByUserAndPost(user.getId(), postId);

        VoteData voteData;

        if (Objects.isNull(userPostVoteHistory)){
            voteData = new VoteData(isUpVote, false, false);
        } else {
            voteData = new VoteData(isUpVote, userPostVoteHistory.getIsUpVoted(), userPostVoteHistory.getIsDownVoted());
        }

        Long rating = changeRating(voteData);

        Post post = postRepository.findOneById(postId);

        Objects.requireNonNull(post, "post cant be empty");

        post.setRating(post.getRating() + rating);

        postRepository.save(post);

        if (Objects.isNull(userPostVoteHistory)){
            userPostVoteHistory = new UserPostVoteHistory();
            userPostVoteHistory.setPost(post);
            userPostVoteHistory.setUser(user);
        }

        userPostVoteHistory.setIsUpVoted(voteData.getDbIsUpVoted());
        userPostVoteHistory.setIsDownVoted(voteData.getDbIsDownVoted());

        userPostVoteHistoryRepository.save(userPostVoteHistory);
    }

    public void commentChangeRating(Boolean isUpVote, User user, Long commentId){

        Objects.requireNonNull(user, "user cant be empty");
        Objects.requireNonNull(commentId, "comment cant be empty");

        UserCommentaryVoteHistory userCommentaryVoteHistory = userCommentaryVoteHistoryRepository.findByUserAndPost(user.getId(), commentId);

        VoteData voteData;
        if (Objects.isNull(userCommentaryVoteHistory)){
            voteData = new VoteData(isUpVote, false, false);
        } else {
            voteData = new VoteData(isUpVote, userCommentaryVoteHistory.getIsUpVoted(), userCommentaryVoteHistory.getIsDownVoted());
        }

        Long rating = changeRating(voteData);

        Commentary commentary = commentaryRepository.findOneById(commentId);

        Objects.requireNonNull(commentary, "post cant be empty");

        commentary.setRating(commentary.getRating() + rating);

        commentaryRepository.save(commentary);

        if (Objects.isNull(userCommentaryVoteHistory)){
            userCommentaryVoteHistory = new UserCommentaryVoteHistory();
            userCommentaryVoteHistory.setCommentary(commentary);
            userCommentaryVoteHistory.setUser(user);
        }

        userCommentaryVoteHistory.setIsUpVoted(voteData.getDbIsUpVoted());
        userCommentaryVoteHistory.setIsDownVoted(voteData.getDbIsDownVoted());

        userCommentaryVoteHistoryRepository.save(userCommentaryVoteHistory);
    }

}
