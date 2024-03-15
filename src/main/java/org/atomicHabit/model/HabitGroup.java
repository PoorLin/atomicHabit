package org.atomicHabit.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HabitGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer habitGroupId;
    @Column(nullable = false)
    private Integer userGroupId;

    public HabitGroup() {
    }

    @Column(nullable = false)
    private String habitGName;

    public Integer getHabitGroupId() {
        return habitGroupId;
    }

    public void setHabitGroupId(Integer habitGroupId) {
        this.habitGroupId = habitGroupId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getHabitGName() {
        return habitGName;
    }

    public void setHabitGName(String habitGName) {
        this.habitGName = habitGName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(nullable = false)
    private Date startDate;
}
