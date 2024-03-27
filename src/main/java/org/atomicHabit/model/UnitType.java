package org.atomicHabit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UnitType implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unitTypeId;
private String unitTypeName;

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    public UnitType() {
    }

    public UnitType(Integer unitTypeId, String unitTypeName) {
        this.unitTypeId = unitTypeId;
        this.unitTypeName = unitTypeName;
    }
}
