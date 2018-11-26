package just.fo.fun.entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;


@Entity
@ToString
@Data
public class translations extends SuperEntity{

    @Column(unique = true, nullable = false)
    private String key;
    private String kk;
    private String en;
    private String ru;
    @Length(max = 1)
    private String chanel;

}
