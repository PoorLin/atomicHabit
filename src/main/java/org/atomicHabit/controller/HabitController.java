package org.atomicHabit.controller;

import org.atomicHabit.model.Habit;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.UnitType;
import org.atomicHabit.model.dto.MakeChartReq;
import org.atomicHabit.model.dto.UpdateHabitStatus;
import org.atomicHabit.service.HabitService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;

import static org.atomicHabit.constance.habitConst.HABIT_VALUE_INVALID;
import static org.atomicHabit.constance.habitConst.SUCCESS;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/habit")
@RestController
public class HabitController {
    private HabitService habitService;

    public HabitController(HabitService habitService){
        this.habitService=habitService;
    }
    @PostMapping("/addHabit")
    public Result addHabit(@RequestBody Habit habit){
        if(habit != null){
            return   habitService.addHabit(habit);
        }else {
            return   new Result<>(HABIT_VALUE_INVALID);
        }

    }


    @PostMapping("/addHabitRecord")
    public Result addHabitRecord(@RequestBody Habit habit){
        return   habitService.addHabitRecord(habit);
    }
    @PutMapping("/{habitId}")
    public Result updateHabit( @RequestBody Habit habit, @PathVariable Integer habitId){
       habit.setHabitId(habitId);
        return  habitService.editHabit(habit);
    }
    @GetMapping("/findUsersHabits/{userId}")
    public Result getUserHabits(@PathVariable Integer userId){
        return habitService.getUserHabits(userId);
    }

    @DeleteMapping("/{habitId}")
    public Result deleteUserHabits(@PathVariable Integer habitId){
        return habitService.deleteHabit(habitId);
    }
    @PostMapping("/makeChart")
    public Result compareHabitWithOther(@RequestBody MakeChartReq makeChartReq){
        return habitService.compareHabitWithOther(makeChartReq);
    }

    @PutMapping("/updateHabitStatus")
    public Result updateHabitStatus( @RequestBody UpdateHabitStatus updateHabitStatus){
        return  habitService.updateHabitStatus(updateHabitStatus);
    }

    @PostMapping("/addUnitType")
    public Result addUnitType(@RequestBody UnitType unitType){
        return habitService.addUnitType(unitType);
    }

    @GetMapping("/getTags")
    public Result getTags(){
        return habitService.getTags();
    }



    @GetMapping("/findOneHabit/{habitId}")
    public Result getHabitById(@PathVariable Integer habitId){
        return habitService.getHabitById(habitId);
    }





}
