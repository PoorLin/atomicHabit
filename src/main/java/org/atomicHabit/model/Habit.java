package org.atomicHabit.model;

import org.atomicHabit.model.embedId.HabitId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Habit {
    @Id
    private HabitId habitId;

    private String habitName;

    private  int hide;

    private Date startDate;

}
