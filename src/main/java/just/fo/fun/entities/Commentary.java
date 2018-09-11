package just.fo.fun.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import just.fo.fun.utils.serializers.LocalDateTimeDeserializer;
import just.fo.fun.utils.serializers.LocalDateTimeSerializer;
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
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    private String text;

    @Length(max = 512)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Commentary parent;

    @Column(columnDefinition="bigint default '0'")
    private Long rating;

    @Column
    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updated;

}
