package org.atomicHabit.model.dto;

import java.io.Serializable;

public class MakeChartReq implements Serializable {
    private  Integer userId ;
    private  String habitName;

    public MakeChartReq() {
    }

    public MakeChartReq(Integer userId, String habitName) {
        this.userId = userId;
        this.habitName = habitName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }
}
