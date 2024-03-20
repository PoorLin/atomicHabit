package org.atomicHabit.controller;

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
    @PostMapping
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
}