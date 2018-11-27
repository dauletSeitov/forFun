package just.fo.fun.post.model;

import just.fo.fun.entities.Category;
import just.fo.fun.entities.Post;
import just.fo.fun.user.model.UserDto;
import just.fo.fun.utils.serializers.Serialize;
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

    @Serialize
    private LocalDateTime updated;
    private Category category;
    private Long rating;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.imageUrl = post.getImageUrl();
        this.updated = post.getUpdated();
        this.category = post.getCategory();
        this.rating = post.getRating();
        this.user = new UserDto(post.getUser());
    }
}
