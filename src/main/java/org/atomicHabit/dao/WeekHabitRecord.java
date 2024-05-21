package org.atomicHabit.dao;


import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

public class WeekHabitRecord implements Serializable {
    private String recordDate;
    private BigInteger habitRecordId;
    private BigInteger habitId;
    private BigInteger isSuccess;
    private BigInteger status;


    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public BigInteger getHabitRecordId() {
        return habitRecordId;
    }

    public void setHabitRecordId(BigInteger habitRecordId) {
        this.habitRecordId = habitRecordId;
    }

    public BigInteger getHabitId() {
        return habitId;
    }

    public void setHabitId(BigInteger habitId) {
        this.habitId = habitId;
    }

    public BigInteger getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(BigInteger isSuccess) {
        this.isSuccess = isSuccess;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }
}
