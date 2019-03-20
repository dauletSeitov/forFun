package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBTranslations is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBTranslations {

    public DBTranslations() {
    }

    public DBTranslations(String chanel, String en, Long id, Byte isDeleted, String key, String kk, String ru) {
        this.chanel = chanel;
        this.en = en;
        this.id = id;
        this.isDeleted = isDeleted;
        this.key = key;
        this.kk = kk;
        this.ru = ru;
    }

    private String chanel;

    private String en;

    private Long id;

    private Byte isDeleted;

    private String key;

    private String kk;

    private String ru;

    public String getChanel() {
        return chanel;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    @Override
    public String toString() {
         return "chanel = " + chanel + ", en = " + en + ", id = " + id + ", isDeleted = " + isDeleted + ", key = " + key + ", kk = " + kk + ", ru = " + ru;
    }

}

