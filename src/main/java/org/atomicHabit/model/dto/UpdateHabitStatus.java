package org.atomicHabit.model.dto;

import java.io.Serializable;

public class UpdateHabitStatus implements Serializable {
    private Integer habitId;
    private  Integer status;

    public Integer getHabitId() {
        return habitId;
    }

    public void setHabitId(Integer habitId) {
        this.habitId = habitId;
    }

    public UpdateHabitStatus(Integer habitId) {
        this.habitId = habitId;
    }

    public UpdateHabitStatus(Integer habitId, Integer status) {
        this.habitId = habitId;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public UpdateHabitStatus() {
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
