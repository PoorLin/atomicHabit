package org.atomicHabit.model;

import org.atomicHabit.model.embedId.HabitId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class HabitRecord {

    public HabitId getHabitId() {
        return habitId;
    }

    public void setHabitId(HabitId habitId) {
        this.habitId = habitId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @EmbeddedId
    private HabitId habitId;

    private Date recordDate;

    private String reason;


}
