package just.fo.fun.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import just.fo.fun.utils.serializers.ConstantsDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static just.fo.fun.constants.Constants.FALSE;

@Data
@ToString
@MappedSuperclass
public class SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "is_deleted", columnDefinition="numeric")
    @JsonDeserialize(using = ConstantsDeserializer.class)
    public Byte deleted = FALSE;

}
