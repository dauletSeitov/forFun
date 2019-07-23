package just.fo.fun.post.model;

import just.fo.fun.user.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;
    private UserDto user;
    private String title;
    private String imageUrl;

    private LocalDateTime updated;
    private String category;
    private Long rating;
    private Long commentCount;
    private Boolean isUpVoted; // true - up voted. false - down voted. null did not do anything

}
