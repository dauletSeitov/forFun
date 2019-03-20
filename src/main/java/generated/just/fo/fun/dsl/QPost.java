package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QPost is a Querydsl query type for DBPost
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPost extends com.querydsl.sql.RelationalPathBase<DBPost> {

    private static final long serialVersionUID = 1524422112;

    public static final QPost post = new QPost("post");

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Byte> isDeleted = createNumber("isDeleted", Byte.class);

    public final NumberPath<Long> rating = createNumber("rating", Long.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final com.querydsl.sql.PrimaryKey<DBPost> postPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBCategory> fkg6l1ydp1pwkmyj166teiuov1b = createForeignKey(categoryId, "id");

    public final com.querydsl.sql.ForeignKey<DBUser> fk51aeb5le008k8klgnyfaalmn = createForeignKey(userId, "id");

    public final com.querydsl.sql.ForeignKey<DBCommentary> _fk7u0oo6wyaothqx4xcjh4ex0u6 = createInvForeignKey(id, "post_id");

    public QPost(String variable) {
        super(DBPost.class, forVariable(variable), "public", "post");
        addMetadata();
    }

    public QPost(String variable, String schema, String table) {
        super(DBPost.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPost(String variable, String schema) {
        super(DBPost.class, forVariable(variable), schema, "post");
        addMetadata();
    }

    public QPost(Path<? extends DBPost> path) {
        super(path.getType(), path.getMetadata(), "public", "post");
        addMetadata();
    }

    public QPost(PathMetadata metadata) {
        super(DBPost.class, metadata, "public", "post");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(categoryId, ColumnMetadata.named("category_id").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(imageUrl, ColumnMetadata.named("image_url").withIndex(3).ofType(Types.VARCHAR).withSize(512));
        addMetadata(isDeleted, ColumnMetadata.named("is_deleted").withIndex(2).ofType(Types.NUMERIC).withSize(1));
        addMetadata(rating, ColumnMetadata.named("rating").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(title, ColumnMetadata.named("title").withIndex(5).ofType(Types.VARCHAR).withSize(255));
        addMetadata(updated, ColumnMetadata.named("updated").withIndex(6).ofType(Types.TIMESTAMP).withSize(29).withDigits(6));
        addMetadata(userId, ColumnMetadata.named("user_id").withIndex(8).ofType(Types.BIGINT).withSize(19));
    }

}

