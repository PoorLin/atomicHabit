package org.atomicHabit.dao;

import org.atomicHabit.model.Habit;
import org.atomicHabit.model.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitDao extends JpaRepository<Habit, Integer> {


    public List<Habit> findByUserId(Integer id);

    public List<Habit> findByHabitNameContaining(String habitName);

    public boolean existsByHabitName(String habitName);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND hr.status != ?2")
    Integer countAllOneHabit(String habitName,Integer status);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND hr.status = ?2")
    Integer countAllOneHabitSuccess(String habitName,Integer status);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND h.userId = ?2 AND hr.status != ?3")
    Integer countMyOneHabit(String habitName,Integer userId,Integer status);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND h.userId = ?2 AND hr.status = ?3")
    Integer countMyOneHabitSuccess(String habitName,Integer userId,Integer status);
}
