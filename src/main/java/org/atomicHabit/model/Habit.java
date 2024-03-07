package org.atomicHabit.model;

import org.atomicHabit.model.embedId.HabitId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Habit {
    @EmbeddedId
    private HabitId habitId;

    private String habitName;

    public HabitId getHabitId() {
        return habitId;
    }

    public void setHabitId(HabitId habitId) {
        this.habitId = habitId;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private  int hide;

    private Date startDate;

}
