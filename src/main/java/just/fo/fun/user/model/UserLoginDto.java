package just.fo.fun.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class UserLoginDto {

    private Long id;

    private String login;

    private String password;

    private String name;

    private LocalDate birthDay;

}
