package org.atomicHabit.model.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class HabitAllsuccRate implements Serializable {
    private Integer is_success;
    private Long succCount;




    public Long getSuccCount() {
        return succCount;
    }

    public Integer getIs_success() {
        return is_success;
    }

    public void setIs_success(Integer is_success) {
        this.is_success = is_success;
    }

    public void setSuccCount(Long succCount) {
        this.succCount = succCount;
    }

    public HabitAllsuccRate() {
    }


    public HabitAllsuccRate(Integer is_success, Long succCount) {
        this.is_success = is_success;
        this.succCount = succCount;
    }

    @Override
    public String toString() {
        return "HabitAllsuccRate{" +
                "is_success=" + is_success +
                ", succCount=" + succCount +
                '}';
    }

}
