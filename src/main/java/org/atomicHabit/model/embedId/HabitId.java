package org.atomicHabit.model.embedId;

import javax.persistence.Embeddable;

@Embeddable
public class HabitId {

    private int habitId;

    private int userId;
}
