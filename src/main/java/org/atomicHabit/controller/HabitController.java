package org.atomicHabit.controller;

import org.atomicHabit.model.Habit;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.dto.UpdateHabitStatus;
import org.atomicHabit.service.HabitService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;


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
            habit.setStartDate(new Date());
            return   habitService.addHabit(habit);
        }else {
            return   habitService.addHabit(habit);
        }

    }


    @PostMapping("/addHabitRecord")
    public Result addHabitRecord(@RequestBody Habit habit){
        return   habitService.addHabitRecord(habit);
    }
    @PutMapping("/{habitId}")
    public Result updateHabit( @RequestBody Habit habit, @PathVariable Integer habitId){
       habit.setHabitId(habitId);
        return  habitService.updateHabit(habit);
    }
    @GetMapping("/{userId}")
    public Result getUserHabits(@PathVariable Integer userId){
        return habitService.getUserHabits(userId);
    }

    @DeleteMapping("/{habitId}")
    public Result deleteUserHabits(@PathVariable Integer habitId){
        return habitService.deleteHabit(habitId);
    }
    @PostMapping("/compareOther")
    public Result compareHabitWithOther(@RequestBody Habit habit){
        return habitService.compareHabitWithOther(habit);
    }

    @PutMapping("/updateHabitStatus")
    public Result updateHabitStatus( @RequestBody UpdateHabitStatus updateHabitStatus){
        return  habitService.updateHabitStatus(updateHabitStatus);
    }
}
