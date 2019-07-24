package just.fo.fun.entities;

import just.fo.fun.user.model.UserState;
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

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserState state;

    private Integer incorrectAttempt;

    private LocalDateTime lockedTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated;

}
