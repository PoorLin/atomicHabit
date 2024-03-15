package org.atomicHabit.model.dto;

import java.io.Serializable;

public class CountRecord implements Serializable {
    private Integer countMy;

    public Integer getCountMy() {
        return countMy;
    }

    public CountRecord() {
    }

    public CountRecord(Integer countMy, Integer countAll) {
        this.countMy = countMy;
        this.countAll = countAll;
    }

    public void setCountMy(Integer countMy) {
        this.countMy = countMy;
    }

    public Integer getCountAll() {
        return countAll;
    }

    public void setCountAll(Integer countAll) {
        this.countAll = countAll;
    }

    private Integer countAll;

}
