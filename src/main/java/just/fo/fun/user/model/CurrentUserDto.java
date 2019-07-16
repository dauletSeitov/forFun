package just.fo.fun.user.model;

import lombok.Data;

@Data
public class CurrentUserDto {

    private Long id;
    private String name;
    private String login;
    private Long myPostRating;
    private Long myPostCount;
    private Long myCommentRating;
    private Long myCommentCount;

    private Long myUpVotes;
    private Long myDownVotes;
    private Long alienCommentRating;
    private Long alienCommentCount;

    private String photoUrl;
}
