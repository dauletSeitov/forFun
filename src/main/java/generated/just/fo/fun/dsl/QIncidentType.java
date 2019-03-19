package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QIncidentType is a Querydsl query type for DBIncidentType
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QIncidentType extends com.querydsl.sql.RelationalPathBase<DBIncidentType> {

    private static final long serialVersionUID = 836471916;

    public static final QIncidentType incidentType = new QIncidentType("incident_type");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<DBIncidentType> incidentTypePkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBIncident> _fkj8ceq1dm9co96dtaa5fbr5kqh = createInvForeignKey(id, "incident_type_id");

    public QIncidentType(String variable) {
        super(DBIncidentType.class, forVariable(variable), "public", "incident_type");
        addMetadata();
    }

    public QIncidentType(String variable, String schema, String table) {
        super(DBIncidentType.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QIncidentType(String variable, String schema) {
        super(DBIncidentType.class, forVariable(variable), schema, "incident_type");
        addMetadata();
    }

    public QIncidentType(Path<? extends DBIncidentType> path) {
        super(path.getType(), path.getMetadata(), "public", "incident_type");
        addMetadata();
    }

    public QIncidentType(PathMetadata metadata) {
        super(DBIncidentType.class, metadata, "public", "incident_type");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(description, ColumnMetadata.named("description").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

