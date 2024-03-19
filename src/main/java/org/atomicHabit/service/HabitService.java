package org.atomicHabit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atomicHabit.dao.HabitDao;
import org.atomicHabit.dao.HabitRecordDao;
import org.atomicHabit.model.Habit;
import org.atomicHabit.model.HabitRecord;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.User;
import org.atomicHabit.model.dto.CountRecord;
import org.atomicHabit.model.embedId.HabitRecordId;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.atomicHabit.constance.habitConst.*;

@Service
public class HabitService {
    private final HabitDao habitDao;
    private final HabitRecordDao habitRecordDao;
    public HabitService(HabitDao habitDao,HabitRecordDao habitRecordDao){
        this.habitDao=habitDao;
        this.habitRecordDao=habitRecordDao;
    }

    public Result addHabit( List<Habit> habitList){
        Date today=new Date();
        for(Habit habit:habitList){
            if(habitDao.existsByHabitName(habit.getHabitName())){ //存在相同習慣
                new Result<>(HABIT_ALREADY_EXIST);
            }else {
                Integer habitId=habitDao.save(habit).getHabitId();
                HabitRecord habitRecord=new HabitRecord(habitId,today,HABITRECORD_INIT);
                habitRecordDao.save(habitRecord);
            }

        }
        return new Result<>(SUCCESS);
    }

    public Result addHabitRecord(Habit habit){
        Date today=new Date();
        Integer habitId=habit.getHabitId();
        HabitRecord habitRecord=new HabitRecord(habitId,today,HABITRECORD_INIT);
        habitRecordDao.save(habitRecord);
        return new Result<>(SUCCESS);
    }


    public Result updateHabitRecord( Habit habit){
        habitDao.save(habit);
       return new Result<>(SUCCESS);
    }
    public Result updateHabit( Habit habit){
        habitDao.save(habit);
        return new Result<>(SUCCESS);
    }

    public Result<List<Habit>> getUserHabits(Integer userId){
        List<Habit> userList=habitDao.findByUserId(userId);
        System.out.println(userList);

        ObjectMapper objectMapper = new ObjectMapper();
        String json=null;
        try {
             json = objectMapper.writeValueAsString(userList);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Result<>(SUCCESS, userList);
    }
    public Result deleteHabit(Integer habitId){
        habitDao.deleteById(habitId);
        return new Result<>(SUCCESS);
    }
    public Result compareHabitWithOther(Habit habit){
         Integer countAll=habitDao.countAllOneHabit(habit.getHabitName(),HABITRECORD_INIT);
        Integer countMy=habitDao.countMyOneHabit(habit.getHabitName(),habit.getUserId(),HABITRECORD_INIT);

        Integer countAllSuccess=habitDao.countAllOneHabitSuccess(habit.getHabitName(),HABITRECORD_SUCCESS);
        Integer countMySuccess=habitDao.countMyOneHabitSuccess(habit.getHabitName(),habit.getUserId(),HABITRECORD_SUCCESS);

        return new Result<>(SUCCESS,new CountRecord(countMy,countAll));
    }

}
