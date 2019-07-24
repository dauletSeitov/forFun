package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTranslations is a Querydsl query type for DBTranslations
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTranslations extends com.querydsl.sql.RelationalPathBase<DBTranslations> {

    private static final long serialVersionUID = 1227579682;

    public static final QTranslations translations = new QTranslations("translations");

    public final StringPath chanel = createString("chanel");

    public final StringPath en = createString("en");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isDeleted = createNumber("isDeleted", Byte.class);

    public final StringPath key = createString("key");

    public final StringPath kk = createString("kk");

    public final StringPath ru = createString("ru");

    public final com.querydsl.sql.PrimaryKey<DBTranslations> translationsPkey = createPrimaryKey(id);

    public QTranslations(String variable) {
        super(DBTranslations.class, forVariable(variable), "public", "translations");
        addMetadata();
    }

    public QTranslations(String variable, String schema, String table) {
        super(DBTranslations.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTranslations(String variable, String schema) {
        super(DBTranslations.class, forVariable(variable), schema, "translations");
        addMetadata();
    }

    public QTranslations(Path<? extends DBTranslations> path) {
        super(path.getType(), path.getMetadata(), "public", "translations");
        addMetadata();
    }

    public QTranslations(PathMetadata metadata) {
        super(DBTranslations.class, metadata, "public", "translations");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(chanel, ColumnMetadata.named("chanel").withIndex(3).ofType(Types.VARCHAR).withSize(1));
        addMetadata(en, ColumnMetadata.named("en").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(2).ofType(Types.NUMERIC).withSize(1));
        addMetadata(key, ColumnMetadata.named("key").withIndex(5).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(kk, ColumnMetadata.named("kk").withIndex(6).ofType(Types.VARCHAR).withSize(255));
        addMetadata(ru, ColumnMetadata.named("ru").withIndex(7).ofType(Types.VARCHAR).withSize(255));
    }

}

