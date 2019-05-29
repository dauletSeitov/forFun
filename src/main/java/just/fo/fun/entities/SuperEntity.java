package just.fo.fun.entities;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;


@Data
@ToString
@MappedSuperclass
public class SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "is_deleted")
    private Boolean deleted = false;

}
