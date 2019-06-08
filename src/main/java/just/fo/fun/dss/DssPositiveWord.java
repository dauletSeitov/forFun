package just.fo.fun.dss;

import just.fo.fun.entities.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
public class DssPositiveWord extends SuperEntity {

    private String word;
}
