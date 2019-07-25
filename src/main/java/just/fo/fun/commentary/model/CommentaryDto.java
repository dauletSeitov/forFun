package just.fo.fun.commentary.model;

import just.fo.fun.user.model.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class CommentaryDto {

    private Long postId;

    private Long parentId;

    private Long id;

    private Long userId;

    private UserDto user;

    private String text;

    private String imageUrl;

    private Long rating;

    private List<CommentaryDto> children;

}
