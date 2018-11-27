package just.fo.fun.commentary.model;

import just.fo.fun.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class CommentaryDto {

    private Long id;

    private User user;

    private String text;

    private String imageUrl;

    private Long rating;

    private List<CommentaryDto> children;

}
