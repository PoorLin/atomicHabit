package org.atomicHabit.model.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class HabitAllsuccRateDTO implements Serializable {
    private BigInteger is_success;
    private BigInteger succCount;









    public HabitAllsuccRateDTO() {
    }


    public HabitAllsuccRateDTO(BigInteger is_success, BigInteger succCount) {
        this.is_success = is_success;
        this.succCount = succCount;
    }

    public BigInteger getSuccCount() {
        return succCount;
    }

    public void setSuccCount(BigInteger succCount) {
        this.succCount = succCount;
    }

    public BigInteger getIs_success() {
        return is_success;
    }

    public void setIs_success(BigInteger is_success) {
        this.is_success = is_success;
    }

    @Override
    public String toString() {
        return "HabitAllsuccRate{" +
                "is_success=" + is_success +
                ", succCount=" + succCount +
                '}';
    }

}
