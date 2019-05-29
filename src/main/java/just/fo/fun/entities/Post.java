package just.fo.fun.entities;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post extends SuperEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Length(max = 512)
    private String imageUrl;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(columnDefinition="bigint default '0'")
    private Long rating;

}
