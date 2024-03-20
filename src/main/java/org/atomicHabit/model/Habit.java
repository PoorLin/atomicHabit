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

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }


    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
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

    public void setHabitRecordList(List<HabitRecord> habitRecordList) {
        this.habitRecordList = habitRecordList;
    }

    private  Integer hide;

    @Override
    public String toString() {
        return "Habit{" +
                "habitId=" + habitId +
                ", userId=" + userId +
                ", habitName='" + habitName + '\'' +
                ", hide=" + hide +
                ", startDate=" + startDate +
                '}';
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

    private Date startDate;
    @OneToMany(mappedBy = "habit",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HabitRecord> habitRecordList;

}
