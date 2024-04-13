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

}
