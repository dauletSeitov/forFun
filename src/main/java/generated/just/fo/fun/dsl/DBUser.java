package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBUser is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBUser {

    public DBUser() {
    }

    public DBUser(Long id, java.math.BigInteger isDeleted, String login, String name, String password, java.sql.Timestamp updated) {
        this.id = id;
        this.isDeleted = isDeleted;
        this.login = login;
        this.name = name;
        this.password = password;
        this.updated = updated;
    }

    private Long id;

    private java.math.BigInteger isDeleted;

    private String login;

    private String name;

    private String password;

    private java.sql.Timestamp updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.math.BigInteger getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(java.math.BigInteger isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.sql.Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(java.sql.Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
         return "id = " + id + ", isDeleted = " + isDeleted + ", login = " + login + ", name = " + name + ", password = " + password + ", updated = " + updated;
    }

}

