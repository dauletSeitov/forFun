package just.fo.fun.entities;

import lombok.Data;
import javax.persistence.*;


@Data
@MappedSuperclass
public class SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Boolean isDeleted = false;

}
