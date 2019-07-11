package just.fo.fun.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @UpdateTimestamp
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(columnDefinition="bigint default '0'")
    private Long rating;

}
