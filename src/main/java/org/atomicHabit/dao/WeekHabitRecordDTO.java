package org.atomicHabit.dao;


import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

public class WeekHabitRecordDTO implements Serializable {
    private Date recordDate;
    private Integer habitRecordId;
    private Integer habitId;
    private Integer isSuccess;
    private Integer status;

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getHabitRecordId() {
        return habitRecordId;
    }

    public void setHabitRecordId(Integer habitRecordId) {
        this.habitRecordId = habitRecordId;
    }

    public Integer getHabitId() {
        return habitId;
    }

    public void setHabitId(Integer habitId) {
        this.habitId = habitId;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
