package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBCategory is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBCategory {

    public DBCategory() {
    }

    public DBCategory(Integer id, java.math.BigInteger isDeleted, String name) {
        this.id = id;
        this.isDeleted = isDeleted;
        this.name = name;
    }

    private Integer id;

    private java.math.BigInteger isDeleted;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.math.BigInteger getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(java.math.BigInteger isDeleted) {
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

