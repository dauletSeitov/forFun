package just.fo.fun.user.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import just.fo.fun.entities.User;
import just.fo.fun.utils.serializers.LocalDateTimeDeserializer;
import just.fo.fun.utils.serializers.LocalDateTimeSerializer;
import just.fo.fun.utils.serializers.Serialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;

    @Serialize
    private LocalDateTime updated;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.updated = user.getUpdated();
    }
}
