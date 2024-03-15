package org.atomicHabit.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HabitGroupRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer habitGId;
    @Column(nullable = false)
    private Integer groupId;

    public Integer getHabitGId() {
        return habitGId;
    }

    public void setHabitGId(Integer habitGId) {
        this.habitGId = habitGId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Column(nullable = false)
    private Integer userId;

    private String reason;

    private Date recordDate;

}
