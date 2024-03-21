package org.atomicHabit.controller;

import org.atomicHabit.model.Habit;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.User;
import org.atomicHabit.service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.atomicHabit.constance.habitConst.SUCCESS;

@RestController
@RequestMapping("/users")
public class UserController {

private final UserService userService;

public UserController(UserService userService){
    this.userService=userService;
}
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/{userId}")
    public Result getUser(@PathVariable Integer userId){
        return userService.getUse(userId);
    }
    @PutMapping("/{userId}")
    public Result updateUser(@RequestBody User user,@PathVariable Integer userId){
        return  userService.updateUser(user,userId);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return   userService.login(user);
    }
    @PostMapping("/loginGoogle")
    public Result loginByGoogle(@RequestBody User user){
        return   userService.loginByGoogle(user.getToken());
    }
    @PostMapping("/forgotPass")
    public Result forgotPass(@RequestBody User user){
        return userService.forgotPass(user);
    }
    @PostMapping("/ChangePass")
    public Result ChangePass(@RequestBody User user){
        return userService.ChangePass(user);
    }

}
