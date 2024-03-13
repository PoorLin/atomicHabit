package org.atomicHabit.model;

import org.atomicHabit.model.embedId.HabitRecordId;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HabitRecord {
    public Date getRecordDate() {
        return recordDate;
    }

    public HabitRecord() {
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getReason() {
        return reason;
    }

    public HabitRecord(HabitRecordId habitId, Date recordDate,Integer status) {
        this.habitId = habitId;
        this.recordDate = recordDate;
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    @EmbeddedId
    private HabitRecordId habitId;
    @Column(nullable = false)
    private Date recordDate;

    private String reason;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;
    @Override
    public String toString() {
        return "HabitRecord{" +
                "habitId=" + habitId +
                ", recordDate=" + recordDate +
                ", reason='" + reason + '\'' +
                '}';
    }

    public HabitRecordId getHabitId() {
        return habitId;
    }

    public void setHabitId(HabitRecordId habitId) {
        this.habitId = habitId;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "habitId" ,insertable = false , updatable = false)
    private Habit habit;

}
