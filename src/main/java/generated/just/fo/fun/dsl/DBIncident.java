package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBIncident is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBIncident {

    public DBIncident() {
    }

    public DBIncident(Long areaId, String atachment, Integer dangerLevel, String description, Long id, Long incidentTypeId, String name, Long vehicleTypeId) {
        this.areaId = areaId;
        this.atachment = atachment;
        this.dangerLevel = dangerLevel;
        this.description = description;
        this.id = id;
        this.incidentTypeId = incidentTypeId;
        this.name = name;
        this.vehicleTypeId = vehicleTypeId;
    }

    private Long areaId;

    private String atachment;

    private Integer dangerLevel;

    private String description;

    private Long id;

    private Long incidentTypeId;

    private String name;

    private Long vehicleTypeId;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAtachment() {
        return atachment;
    }

    public void setAtachment(String atachment) {
        this.atachment = atachment;
    }

    public Integer getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(Integer dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncidentTypeId() {
        return incidentTypeId;
    }

    public void setIncidentTypeId(Long incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    @Override
    public String toString() {
         return "areaId = " + areaId + ", atachment = " + atachment + ", dangerLevel = " + dangerLevel + ", description = " + description + ", id = " + id + ", incidentTypeId = " + incidentTypeId + ", name = " + name + ", vehicleTypeId = " + vehicleTypeId;
    }

}

