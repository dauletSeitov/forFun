package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCategory is a Querydsl query type for DBCategory
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCategory extends com.querydsl.sql.RelationalPathBase<DBCategory> {

    private static final long serialVersionUID = 684350014;

    public static final QCategory category = new QCategory("category");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<java.math.BigInteger> isDeleted = createNumber("isDeleted", java.math.BigInteger.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<DBCategory> categoryPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBPost> _fkg6l1ydp1pwkmyj166teiuov1b = createInvForeignKey(id, "category_id");

    public QCategory(String variable) {
        super(DBCategory.class, forVariable(variable), "public", "category");
        addMetadata();
    }

    public QCategory(String variable, String schema, String table) {
        super(DBCategory.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCategory(String variable, String schema) {
        super(DBCategory.class, forVariable(variable), schema, "category");
        addMetadata();
    }

    public QCategory(Path<? extends DBCategory> path) {
        super(path.getType(), path.getMetadata(), "public", "category");
        addMetadata();
    }

    public QCategory(PathMetadata metadata) {
        super(DBCategory.class, metadata, "public", "category");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(3).ofType(Types.NUMERIC).withSize(131089));
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

