package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QIncident is a Querydsl query type for DBIncident
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QIncident extends com.querydsl.sql.RelationalPathBase<DBIncident> {

    private static final long serialVersionUID = 720822802;

    public static final QIncident incident = new QIncident("incident");

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

    public final StringPath atachment = createString("atachment");

    public final NumberPath<Integer> dangerLevel = createNumber("dangerLevel", Integer.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> incidentTypeId = createNumber("incidentTypeId", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> vehicleTypeId = createNumber("vehicleTypeId", Long.class);

    public final com.querydsl.sql.PrimaryKey<DBIncident> incidentPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBVehicleType> fk3gb1yttw6uu2gc2q35j2hvg70 = createForeignKey(vehicleTypeId, "id");

    public final com.querydsl.sql.ForeignKey<DBIncidentType> fkj8ceq1dm9co96dtaa5fbr5kqh = createForeignKey(incidentTypeId, "id");

    public final com.querydsl.sql.ForeignKey<DBArea> fk51l7iw8gnyjlwxp4fc34q6wxe = createForeignKey(areaId, "id");

    public QIncident(String variable) {
        super(DBIncident.class, forVariable(variable), "public", "incident");
        addMetadata();
    }

    public QIncident(String variable, String schema, String table) {
        super(DBIncident.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QIncident(String variable, String schema) {
        super(DBIncident.class, forVariable(variable), schema, "incident");
        addMetadata();
    }

    public QIncident(Path<? extends DBIncident> path) {
        super(path.getType(), path.getMetadata(), "public", "incident");
        addMetadata();
    }

    public QIncident(PathMetadata metadata) {
        super(DBIncident.class, metadata, "public", "incident");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(areaId, ColumnMetadata.named("area_id").withIndex(6).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(atachment, ColumnMetadata.named("atachment").withIndex(2).ofType(Types.VARCHAR).withSize(255));
        addMetadata(dangerLevel, ColumnMetadata.named("danger_level").withIndex(3).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(description, ColumnMetadata.named("description").withIndex(4).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(incidentTypeId, ColumnMetadata.named("incident_type_id").withIndex(7).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(5).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(vehicleTypeId, ColumnMetadata.named("vehicle_type_id").withIndex(8).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

