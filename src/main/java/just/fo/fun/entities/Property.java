package just.fo.fun.entities;

import just.fo.fun.property.servise.PropertyService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property extends SuperEntity{

    @Enumerated(EnumType.STRING)
    private PropertyService.PropertyCode code;
    private String name;
    private String value;

}