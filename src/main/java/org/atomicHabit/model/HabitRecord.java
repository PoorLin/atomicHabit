package org.atomicHabit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.atomicHabit.model.embedId.HabitRecordId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class HabitRecord implements Serializable {
    public Date getRecordDate() {
        return recordDate;
    }

    private Integer isSuccess;

    public HabitRecord() {
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }



    public HabitRecord(Integer habitId,Date recordDate,Integer status) {
        this.habitId=habitId;
        this.recordDate = recordDate;
        this.status = status;
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
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date recordDate;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "habitId" ,insertable = false , updatable = false)
    private Habit habit;

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }
}
