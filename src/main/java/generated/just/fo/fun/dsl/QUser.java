package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUser is a Querydsl query type for DBUser
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QUser extends com.querydsl.sql.RelationalPathBase<DBUser> {

    private static final long serialVersionUID = 1524574475;

    public static final QUser user = new QUser("user");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigInteger> isDeleted = createNumber("isDeleted", java.math.BigInteger.class);

    public final StringPath login = createString("login");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final com.querydsl.sql.PrimaryKey<DBUser> userPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBCommentary> _fknbelkrdwcbruk4rwm2nxvm1g0 = createInvForeignKey(id, "user_id");

    public final com.querydsl.sql.ForeignKey<DBPost> _fk51aeb5le008k8klgnyfaalmn = createInvForeignKey(id, "user_id");

    public QUser(String variable) {
        super(DBUser.class, forVariable(variable), "public", "user");
        addMetadata();
    }

    public QUser(String variable, String schema, String table) {
        super(DBUser.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUser(String variable, String schema) {
        super(DBUser.class, forVariable(variable), schema, "user");
        addMetadata();
    }

    public QUser(Path<? extends DBUser> path) {
        super(path.getType(), path.getMetadata(), "public", "user");
        addMetadata();
    }

    public QUser(PathMetadata metadata) {
        super(DBUser.class, metadata, "public", "user");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(6).ofType(Types.NUMERIC).withSize(131089));
        addMetadata(login, ColumnMetadata.named("login").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(password, ColumnMetadata.named("password").withIndex(4).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(updated, ColumnMetadata.named("updated").withIndex(5).ofType(Types.TIMESTAMP).withSize(29).withDigits(6));
    }

}

