package org.atomicHabit.dao;

import org.atomicHabit.model.Habit;
import org.atomicHabit.model.HabitRecord;
import org.atomicHabit.model.dto.HabitAllsuccRate;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.sql.Date;
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

    @Query(value ="SELECT a.record_date,COALESCE(hr.habit_record_id,0) as habit_record_id,COALESCE(hr.habit_id,?1) as habit_id,\n" +
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
            "select * from habit_record where habit_id = ?1  \n" +
            ") as hr on a.record_date = DATE(hr.record_date) order by a.record_date;" , nativeQuery = true)
    List<Object[]> findLatestWeekRecord(Integer userId);

    @Query(value = " select a.is_success,COALESCE( b.succCount ,0) as succCount from \n" +
            "    ( SELECT 1 AS is_success UNION SELECT 0 AS is_success) as a  left join (select hc.is_success,count(*) as succCount from habit as h join habit_record as hc on h.habit_id=hc.habit_id where h.user_id=?1 group by hc.is_success) as b \n" +
            "    on a.is_success = b.is_success", nativeQuery = true)
    List<Object[]> countHabitRecordSuccessByUserId(@Param("userId") Integer userId);

    @Query(value = " select a.is_success,COALESCE( b.succCount ,0) as succCount from \n" +
            "    ( SELECT 1 AS is_success UNION SELECT 0 AS is_success) as a  left join (select hc.is_success,count(*) as succCount from habit as h join habit_record as hc on h.habit_id=hc.habit_id where h.user_id=?1 and year(hc.record_date) = ?2 group by hc.is_success) as b \n" +
            "    on a.is_success = b.is_success", nativeQuery = true)
    List<Object[]> countHabitRecordSuccessByUserIdAndYear(@Param("userId") Integer userId,@Param("year")Integer year);

    @Query(value = " select a.is_success,COALESCE( b.succCount ,0) as succCount from \n" +
            "    ( SELECT 1 AS is_success UNION SELECT 0 AS is_success) as a  left join (select  hc.is_success,count(*) as succCount from  habit as h join habit_record as hc on h.habit_id=hc.habit_id  where h.user_id=?1 and concat(year(hc.record_date),'-',LPAD(MONTH(hc.record_date), 2, '0')) = ?2 group by hc.is_success) as b \n" +
            "    on a.is_success = b.is_success", nativeQuery = true)
    List<Object[]> countHabitRecordSuccessByUserIdAndYM(Integer userId,String yearAndMonth);

    @Query("SELECT distinct year(hr.recordDate) " +
            " from Users as u join Habit h on u.userId=h.userId join HabitRecord hr on h.habitId=hr.habitId where u.userId = :userId order by year(hr.recordDate) ")
    List<Integer> getHrExistYears(@Param("userId") Integer userId);

    @Query("SELECT distinct concat(year(hc.recordDate),'-',LPAD(MONTH(hc.recordDate), 2, '0')) " +
            " from Habit as h join HabitRecord as hc on h.habitId=hc.habitId where h.userId=1 order by concat(year(hc.recordDate),'-',LPAD(MONTH(hc.recordDate), 2, '0')) ")
    List<String> getHrExistYearAndMonth(@Param("userId") Integer userId);



}
