package org.atomicHabit.model.embedId;

import javax.persistence.Embeddable;

@Embeddable
public class HabitId {
    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int habitId;

    private int userId;
}
