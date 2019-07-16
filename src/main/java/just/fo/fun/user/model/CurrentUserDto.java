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

    private Long myPostUpVotes;
    private Long myPostDownVotes;
    private Long myCommentUpVotes;
    private Long myCommentDownVotes;

    private String photoUrl;
}
