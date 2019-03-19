package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QHibernateSequences is a Querydsl query type for DBHibernateSequences
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QHibernateSequences extends com.querydsl.sql.RelationalPathBase<DBHibernateSequences> {

    private static final long serialVersionUID = 456610272;

    public static final QHibernateSequences hibernateSequences = new QHibernateSequences("hibernate_sequences");

    public final StringPath sequenceName = createString("sequenceName");

    public final NumberPath<Long> sequenceNextHiValue = createNumber("sequenceNextHiValue", Long.class);

    public final com.querydsl.sql.PrimaryKey<DBHibernateSequences> hibernateSequencesPkey = createPrimaryKey(sequenceName);

    public QHibernateSequences(String variable) {
        super(DBHibernateSequences.class, forVariable(variable), "public", "hibernate_sequences");
        addMetadata();
    }

    public QHibernateSequences(String variable, String schema, String table) {
        super(DBHibernateSequences.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QHibernateSequences(String variable, String schema) {
        super(DBHibernateSequences.class, forVariable(variable), schema, "hibernate_sequences");
        addMetadata();
    }

    public QHibernateSequences(Path<? extends DBHibernateSequences> path) {
        super(path.getType(), path.getMetadata(), "public", "hibernate_sequences");
        addMetadata();
    }

    public QHibernateSequences(PathMetadata metadata) {
        super(DBHibernateSequences.class, metadata, "public", "hibernate_sequences");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(sequenceName, ColumnMetadata.named("sequence_name").withIndex(1).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(sequenceNextHiValue, ColumnMetadata.named("sequence_next_hi_value").withIndex(2).ofType(Types.BIGINT).withSize(19));
    }

}

