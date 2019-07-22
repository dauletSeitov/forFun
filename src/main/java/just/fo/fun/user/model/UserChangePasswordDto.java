package just.fo.fun.user.model;

import lombok.Data;

@Data
public class UserChangePasswordDto {

    private String oldPassword;
    private String newPassword;
}
