package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBArea is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBArea {

    public DBArea() {
    }

    public DBArea(Integer dangerLevel, String description, Long id, String name) {
        this.dangerLevel = dangerLevel;
        this.description = description;
        this.id = id;
        this.name = name;
    }

    private Integer dangerLevel;

    private String description;

    private Long id;

    private String name;

    public Integer getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(Integer dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

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
         return "dangerLevel = " + dangerLevel + ", description = " + description + ", id = " + id + ", name = " + name;
    }

}

