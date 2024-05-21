package org.atomicHabit.dao;


import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

public class WeekHabitRecord implements Serializable {
    private Date recordDate;
    private BigInteger habitRecordId;
    private BigInteger habitId;
    private BigInteger isSuccess;
    private BigInteger status;

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
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
