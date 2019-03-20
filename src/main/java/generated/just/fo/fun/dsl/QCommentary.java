package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCommentary is a Querydsl query type for DBCommentary
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCommentary extends com.querydsl.sql.RelationalPathBase<DBCommentary> {

    private static final long serialVersionUID = 132991209;

    public static final QCommentary commentary = new QCommentary("commentary");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Byte> isDeleted = createNumber("isDeleted", Byte.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> rating = createNumber("rating", Long.class);

    public final StringPath text = createString("text");

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final com.querydsl.sql.PrimaryKey<DBCommentary> commentaryPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBPost> fk7u0oo6wyaothqx4xcjh4ex0u6 = createForeignKey(postId, "id");

    public final com.querydsl.sql.ForeignKey<DBCommentary> fkfiq3wbxgdhom8ewyh4onq9c41 = createForeignKey(parentId, "id");

    public final com.querydsl.sql.ForeignKey<DBUser> fknbelkrdwcbruk4rwm2nxvm1g0 = createForeignKey(userId, "id");

    public final com.querydsl.sql.ForeignKey<DBCommentary> _fkfiq3wbxgdhom8ewyh4onq9c41 = createInvForeignKey(id, "parent_id");

    public QCommentary(String variable) {
        super(DBCommentary.class, forVariable(variable), "public", "commentary");
        addMetadata();
    }

    public QCommentary(String variable, String schema, String table) {
        super(DBCommentary.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCommentary(String variable, String schema) {
        super(DBCommentary.class, forVariable(variable), schema, "commentary");
        addMetadata();
    }

    public QCommentary(Path<? extends DBCommentary> path) {
        super(path.getType(), path.getMetadata(), "public", "commentary");
        addMetadata();
    }

    public QCommentary(PathMetadata metadata) {
        super(DBCommentary.class, metadata, "public", "commentary");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(imageUrl, ColumnMetadata.named("image_url").withIndex(3).ofType(Types.VARCHAR).withSize(512));
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(2).ofType(Types.NUMERIC).withSize(1));
        addMetadata(parentId, ColumnMetadata.named("parent_id").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(postId, ColumnMetadata.named("post_id").withIndex(8).ofType(Types.BIGINT).withSize(19));
        addMetadata(rating, ColumnMetadata.named("rating").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(text, ColumnMetadata.named("text").withIndex(5).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(updated, ColumnMetadata.named("updated").withIndex(6).ofType(Types.TIMESTAMP).withSize(29).withDigits(6));
        addMetadata(userId, ColumnMetadata.named("user_id").withIndex(9).ofType(Types.BIGINT).withSize(19));
    }

}

