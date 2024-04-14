package org.atomicHabit.controller;

import org.atomicHabit.model.Result;
import org.atomicHabit.model.Users;
import org.atomicHabit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

private final UserService userService;

public UserController(UserService userService){
    this.userService=userService;
}
    @PostMapping("/addUser")
    public Result addUser(@RequestBody Users user){
        return userService.addUser(user);
    }
    @GetMapping("/{userId}")
    public Result getUser(@PathVariable Integer userId){
        return userService.getUse(userId);
    }
    @PutMapping("/{userId}")
    public Result updateUser(@RequestBody Users user, @PathVariable Integer userId){
        return  userService.updateUser(user,userId);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Users user){
        return   userService.login(user);
    }
    @PostMapping("/loginGoogle")
    public Result loginByGoogle(@RequestBody Users user){
        return   userService.loginByGoogle(user.getToken());
    }
    @PostMapping("/forgotPass")
    public Result forgotPass(@RequestBody Users user){
        return userService.forgotPass(user);
    }
    @PostMapping("/ChangePass")
    public Result ChangePass(@RequestBody Users user){
        return userService.ChangePass(user);
    }


    @GetMapping("/findLatestWeekRecord/{habitId}")
    public Result findLatestWeekRecord(@PathVariable Integer habitId){
        return userService.findLatestWeekRecord(habitId);
    }

    @GetMapping("/findSuccRate/{userId}")
    public Result findUsersHabitSuccRate(@PathVariable Integer userId){
        return userService.findUsersHabitSuccRate(userId);
    }

    @GetMapping("/findSuccRateYear")
    public Result findUsersHabitSuccRateYear(  @RequestParam("userId") Integer userId,
                                               @RequestParam("year") Integer year){
        return userService.findUsersHabitSuccRateYear(userId,year);
    }

    @GetMapping("/findSuccRateYM")
    public Result findUsersHabitSuccRateYear(  @RequestParam("userId") Integer userId,
                                               @RequestParam("ym") String yearAndMonth){
        return userService.countHabitRecordSuccessByUserIdAndYM(userId,yearAndMonth);
    }
    @GetMapping("/getHrExistYears/{userId}")
    public Result getHrExistYears(@PathVariable Integer userId){
        return userService.getHrExistYears(userId);
    }

    @GetMapping("/getHrExistYearAndMonth/{userId}")
    public Result getHrExistYearAndMonth(@PathVariable Integer userId){
        return userService.getHrExistYearAndMonth(userId);
    }

}
