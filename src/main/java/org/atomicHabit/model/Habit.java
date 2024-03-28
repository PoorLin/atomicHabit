package org.atomicHabit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
public class Habit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer habitId;

    private Integer userId;
    @Column(nullable = false)
    private String habitName;


    private String habitTarget;

    private  Integer status;

    private Date startDate;

    @Column(nullable = false)
    private Integer type;


    private Integer unitTypeId;

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    @OneToMany(mappedBy = "habit",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HabitRecord> habitRecordList;


    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<HabitRecord> getHabitRecordList() {
        return habitRecordList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setHabitRecordList(List<HabitRecord> habitRecordList) {
        this.habitRecordList = habitRecordList;
    }


    public Integer getHabitId() {
        return habitId;
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


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHabitTarget() {
        return habitTarget;
    }

    public void setHabitTarget(String habitTarget) {
        this.habitTarget = habitTarget;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "habitId=" + habitId +
                ", userId=" + userId +
                ", habitName='" + habitName + '\'' +
                ", habitTarget='" + habitTarget + '\'' +
                ", status=" + status +
                ", startDate=" + startDate +
                ", type=" + type +
                ", unitTypeId=" + unitTypeId +
                '}';
    }
}
