package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBIncidentType is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBIncidentType {

    public DBIncidentType() {
    }

    public DBIncidentType(String description, Long id, String name) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    private String description;

    private Long id;

    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
         return "description = " + description + ", id = " + id + ", name = " + name;
    }

}

