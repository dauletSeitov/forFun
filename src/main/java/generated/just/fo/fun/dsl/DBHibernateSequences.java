package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBHibernateSequences is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBHibernateSequences {

    public DBHibernateSequences() {
    }

    public DBHibernateSequences(String sequenceName, Long sequenceNextHiValue) {
        this.sequenceName = sequenceName;
        this.sequenceNextHiValue = sequenceNextHiValue;
    }

    private String sequenceName;

    private Long sequenceNextHiValue;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Long getSequenceNextHiValue() {
        return sequenceNextHiValue;
    }

    public void setSequenceNextHiValue(Long sequenceNextHiValue) {
        this.sequenceNextHiValue = sequenceNextHiValue;
    }

    @Override
    public String toString() {
         return "sequenceName = " + sequenceName + ", sequenceNextHiValue = " + sequenceNextHiValue;
    }

}

