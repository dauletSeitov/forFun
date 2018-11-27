package just.fo.fun.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@NoArgsConstructor
public class UserLoginDto {

    private Long id;
    @NotNull
    @Size(min = 8, max = 124)
    private String login;
    @NotNull
    @Size(min = 6, max = 12)
    private String password;
    @NotNull
    @Size(min = 1, max = 225)
    private String name;

}
