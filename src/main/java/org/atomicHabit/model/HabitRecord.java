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

    public HabitRecord(Integer habitId,Date recordDate,Integer status) {
        this.habitId=habitId;
        this.recordDate = recordDate;
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer habitRecordId;

    public Integer getHabitId() {
        return habitId;
    }

    public Integer getHabitRecordId() {
        return habitRecordId;
    }

    public void setHabitRecordId(Integer habitRecordId) {
        this.habitRecordId = habitRecordId;
    }

    public void setHabitId(Integer habitId) {
        this.habitId = habitId;
    }


    private Integer habitId;
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

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "habitId" ,insertable = false , updatable = false)
    private Habit habit;

}
