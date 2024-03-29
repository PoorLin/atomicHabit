package org.atomicHabit.dao;

import org.atomicHabit.model.Habit;
import org.atomicHabit.model.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface HabitDao extends JpaRepository<Habit, Integer> {


    public List<Habit> findByUserIdOrderByHabitId(Integer id);

    public List<Habit> findByHabitNameContaining(String habitName);

    public boolean existsByHabitNameAndUserId(String habitName,Integer userId);

    public boolean existsByHabitIdNotAndHabitNameAndUserId(Integer habitId,String habitName,Integer userId);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND hr.status != ?2")
    Integer countAllOneHabit(String habitName,Integer status);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND hr.status = ?2")
    Integer countAllOneHabitSuccess(String habitName,Integer status);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND h.userId = ?2 AND hr.status != ?3")
    Integer countMyOneHabit(String habitName,Integer userId,Integer status);

    @Query("select count(*) from Habit h left outer join HabitRecord hr on h.habitId=hr.habitId where h.habitName like %?1% AND h.userId = ?2 AND hr.status = ?3")
    Integer countMyOneHabitSuccess(String habitName,Integer userId,Integer status);

    @Query(value ="SELECT a.record_date,COALESCE(hr.habit_record_id,0) as habit_record_id,COALESCE(hr.habit_id,0) as habit_id,\n" +
            "COALESCE(hr.is_success,0) as is_success,COALESCE(hr.status,0) as status from ( \n" +
            "select date_sub(CURDATE(),INTERVAL 5 day) AS record_date\n " +
            "union all \n" +
            "SELECT date_sub(CURDATE(),INTERVAL 4 day) \n" +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 3 day) \n " +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 2 day) \n" +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 1 day) \n" +
            "union all \n" +
            "select CURDATE()\n" +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 6 day) \n" +
            ") as a left join (\n" +
            "select * from habit_record where habit_id = ?1 \n" +
            ") as hr on a.record_date = DATE(hr.record_date);" , nativeQuery = true)
    List<Object[]> findLatestWeekRecord(Integer habitId);




    @Query("SELECT new HabitRecord () a.record_date,COALESCE(hr.habit_record_id,0) as habit_record_id,COALESCE(hr.habit_id,0) as habit_id,\n" +
            "COALESCE(hr.is_success,0) as is_success,COALESCE(hr.status,0) as status from ( \n" +
            "select date_sub(CURDATE(),INTERVAL 5 day) AS record_date\n " +
            "union all \n" +
            "SELECT date_sub(CURDATE(),INTERVAL 4 day) \n" +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 3 day) \n " +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 2 day) \n" +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 1 day) \n" +
            "union all \n" +
            "select CURDATE()\n" +
            "union all \n" +
            "select date_sub(CURDATE(),INTERVAL 6 day) \n" +
            ") as a left join (\n" +
            "select * from habit_record where habit_id = ?1 \n" +
            ") as hr on a.record_date = DATE(hr.record_date);" )
    WeekHabitRecord findTest(Long id);


}
