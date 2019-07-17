package just.fo.fun.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
public class User extends SuperEntity{

    private String login;

    private String password;

    private String name;

    private String photoUrl;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated;

}
