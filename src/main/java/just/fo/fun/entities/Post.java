package just.fo.fun.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends SuperEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String imageUrl;

    private String tag;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated;

    @UpdateTimestamp
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Long rating;

}
