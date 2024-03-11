package org.atomicHabit.model.embedId;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class HabitRecordId implements Serializable {


    public HabitRecordId() {
    }


    public Integer getHabitId() {
        return habitId;
    }

    public HabitRecordId(Integer habitId, Integer userId) {
        this.habitId = habitId;
        this.userId = userId;
    }

    public void setHabitId(Integer habitId) {
        this.habitId = habitId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    private Integer habitId;

    @Override
    public String toString() {
        return "HabitRecordId{" +
                "habitId=" + habitId +
                ", userId=" + userId +
                '}';
    }

    private Integer userId;


}
