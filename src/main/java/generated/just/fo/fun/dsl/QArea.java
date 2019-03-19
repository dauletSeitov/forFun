package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QArea is a Querydsl query type for DBArea
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QArea extends com.querydsl.sql.RelationalPathBase<DBArea> {

    private static final long serialVersionUID = 1523977677;

    public static final QArea area = new QArea("area");

    public final NumberPath<Integer> dangerLevel = createNumber("dangerLevel", Integer.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<DBArea> areaPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBIncident> _fk51l7iw8gnyjlwxp4fc34q6wxe = createInvForeignKey(id, "area_id");

    public QArea(String variable) {
        super(DBArea.class, forVariable(variable), "public", "area");
        addMetadata();
    }

    public QArea(String variable, String schema, String table) {
        super(DBArea.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QArea(String variable, String schema) {
        super(DBArea.class, forVariable(variable), schema, "area");
        addMetadata();
    }

    public QArea(Path<? extends DBArea> path) {
        super(path.getType(), path.getMetadata(), "public", "area");
        addMetadata();
    }

    public QArea(PathMetadata metadata) {
        super(DBArea.class, metadata, "public", "area");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(dangerLevel, ColumnMetadata.named("danger_level").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(description, ColumnMetadata.named("description").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(4).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

