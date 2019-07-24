package just.fo.fun.entities;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Entity
@ToString
@Data
public class Commentary extends SuperEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(nullable = false)
    private String text;

    @Length(max = 512)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Commentary parent;

    @Column(columnDefinition="bigint default '0'")
    private Long rating;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated;

}
