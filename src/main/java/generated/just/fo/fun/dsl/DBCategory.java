package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBCategory is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBCategory {

    public DBCategory() {
    }

    public DBCategory(Long id, Byte isDeleted, String name) {
        this.id = id;
        this.isDeleted = isDeleted;
        this.name = name;
    }

    private Long id;

    private Byte isDeleted;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
         return "id = " + id + ", isDeleted = " + isDeleted + ", name = " + name;
    }

}

