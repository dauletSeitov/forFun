package generated.just.fo.fun.dsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVehicleType is a Querydsl query type for DBVehicleType
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QVehicleType extends com.querydsl.sql.RelationalPathBase<DBVehicleType> {

    private static final long serialVersionUID = -2064877754;

    public static final QVehicleType vehicleType = new QVehicleType("vehicle_type");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<DBVehicleType> vehicleTypePkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<DBIncident> _fk3gb1yttw6uu2gc2q35j2hvg70 = createInvForeignKey(id, "vehicle_type_id");

    public QVehicleType(String variable) {
        super(DBVehicleType.class, forVariable(variable), "public", "vehicle_type");
        addMetadata();
    }

    public QVehicleType(String variable, String schema, String table) {
        super(DBVehicleType.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVehicleType(String variable, String schema) {
        super(DBVehicleType.class, forVariable(variable), schema, "vehicle_type");
        addMetadata();
    }

    public QVehicleType(Path<? extends DBVehicleType> path) {
        super(path.getType(), path.getMetadata(), "public", "vehicle_type");
        addMetadata();
    }

    public QVehicleType(PathMetadata metadata) {
        super(DBVehicleType.class, metadata, "public", "vehicle_type");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(description, ColumnMetadata.named("description").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

