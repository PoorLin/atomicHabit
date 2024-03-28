package org.atomicHabit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atomicHabit.dao.HabitDao;
import org.atomicHabit.dao.HabitRecordDao;
import org.atomicHabit.dao.UnitTypeDao;
import org.atomicHabit.model.*;
import org.atomicHabit.model.dto.CountRecord;
import org.atomicHabit.model.dto.MakeChartReq;
import org.atomicHabit.model.dto.UpdateHabitStatus;
import org.atomicHabit.model.embedId.HabitRecordId;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.atomicHabit.constance.habitConst.*;

@Service
public class HabitService {
    private final HabitDao habitDao;
    private final HabitRecordDao habitRecordDao;

    private  final UnitTypeDao unitTypeDao;
    public HabitService(HabitDao habitDao,HabitRecordDao habitRecordDao,UnitTypeDao unitTypeDao){
        this.habitDao=habitDao;
        this.habitRecordDao=habitRecordDao;
        this.unitTypeDao=unitTypeDao;
    }

    public Result addHabit( Habit habit){
        Date today=new Date();
            if(habitDao.existsByHabitNameAndUserId(habit.getHabitName(),habit.getUserId())){ //存在相同習慣
              return new Result<>(HABIT_ALREADY_EXIST);
            }else {
                habit.setStartDate(today);
                habit.setStatus(HABITRECORD_INIT);
                habit=habitDao.save(habit);
            }
        return new Result<>(SUCCESS,habit);
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
        Optional<Habit> nowHabitOpt=habitDao.findById(habit.getHabitId());
        if(nowHabitOpt.isPresent()){
            Habit nowHabit=nowHabitOpt.get();
            String newName=habit.getHabitName();
            if(habitDao.existsByHabitNameAndUserId(newName,habit.getUserId())) return new Result<>(HABIT_ALREADY_EXIST);
            else {
                nowHabit.setHabitTarget(habit.getHabitTarget());
                nowHabit.setUnitTypeId(habit.getUnitTypeId());
                nowHabit.setHabitName(habit.getHabitName());
                nowHabit.setStatus(habit.getStatus());
                habitDao.save(nowHabit);
                return new Result<>(SUCCESS);
            }
        }else {
            return new Result<>(HABIT_NOT_EXIST);
        }
    }

    public Result updateHabitStatus( UpdateHabitStatus updateHabitStatus){

        Optional<Habit> nowHabitOpt=habitDao.findById(updateHabitStatus.getHabitId());
        if(nowHabitOpt.isPresent()){
            Habit nowHabit=nowHabitOpt.get();
              nowHabit.setStatus(updateHabitStatus.getStatus());
            habitDao.save(nowHabit);
            return new Result<>(SUCCESS);
        }else {
            return new Result<>(HABIT_NOT_EXIST);
        }
    }

    public Result<List<Habit>> getUserHabits(Integer userId){
        List<Habit> userList=habitDao.findByUserIdOrderByHabitId(userId);
        return new Result<>(SUCCESS, userList);
    }

    public Result<Habit> getHabitById(Integer userId){
        Optional<Habit> optionalHabit=habitDao.findById(userId);
        if(optionalHabit.isPresent()){
            return new Result<>(SUCCESS, optionalHabit.get());
        }else {
            return new Result<>(HABIT_NOT_EXIST) ;
        }
    }
    public Result deleteHabit(Integer habitId){
        habitDao.deleteById(habitId);
        return new Result<>(SUCCESS);
    }
    public Result compareHabitWithOther(MakeChartReq makeChartReq){
         Integer countAllUser=habitDao.countAllOneHabit(makeChartReq.getHabitName(),HABITRECORD_INIT);
        Integer countAllUserSuccess=habitDao.countAllOneHabitSuccess(makeChartReq.getHabitName(),HABITRECORD_SUCCESS);
        Integer countMySuccess=habitDao.countMyOneHabitSuccess(makeChartReq.getHabitName(),makeChartReq.getUserId(),HABITRECORD_SUCCESS);
        Integer countMyAll=habitDao.countMyOneHabit(makeChartReq.getHabitName(),makeChartReq.getUserId(),HABITRECORD_INIT);
        return new Result<>(SUCCESS,new CountRecord(countMyAll,countMySuccess,countAllUser,countAllUserSuccess));
    }

    public Result addUnitType(UnitType unitType){
        if (unitType != null){
            unitTypeDao.save(unitType);
            return new Result<>(SUCCESS);
        }else {
            return new Result<>(DATA_ERROR);
        }

    }

    public Result getTags(){
        List<UnitType> unitTypes=unitTypeDao.findAll();
        return new Result<>(SUCCESS,unitTypes);
    }

}
