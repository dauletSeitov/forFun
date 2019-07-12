package just.fo.fun.user.model;

import lombok.Data;

@Data
public class CurrentUserDto {

    private Long id;
    private String name;
    private String login;
    private Long PostRating;
    private Long PostCount;
    private Long CommentRating;
    private Long CommentCount;
    private String photoUrl;
}
