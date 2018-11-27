package just.fo.fun.entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@ToString
@Data
@Table(name = "\"user\"")
public class User extends SuperEntity{

    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated;

}
