package org.atomicHabit.model.dto;

import java.io.Serializable;

public class CountRecord implements Serializable {
    private Integer myAll;
    private Integer myAllSuccess;
    private Integer allUserAll;
    private Integer allUserAllSuccess;

    public CountRecord(Integer myAll, Integer myAllSuccess, Integer allUserAll, Integer allUserAllSuccess) {
        this.myAll = myAll;
        this.myAllSuccess = myAllSuccess;
        this.allUserAll = allUserAll;
        this.allUserAllSuccess = allUserAllSuccess;
    }

    public Integer getMyAll() {
        return myAll;
    }

    public void setMyAll(Integer myAll) {
        this.myAll = myAll;
    }

    public Integer getMyAllSuccess() {
        return myAllSuccess;
    }

    public void setMyAllSuccess(Integer myAllSuccess) {
        this.myAllSuccess = myAllSuccess;
    }

    public Integer getAllUserAll() {
        return allUserAll;
    }

    public void setAllUserAll(Integer allUserAll) {
        this.allUserAll = allUserAll;
    }

    public Integer getAllUserAllSuccess() {
        return allUserAllSuccess;
    }

    public void setAllUserAllSuccess(Integer allUserAllSuccess) {
        this.allUserAllSuccess = allUserAllSuccess;
    }
}
