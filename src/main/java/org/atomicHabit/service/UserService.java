package org.atomicHabit.service;

import org.atomicHabit.dao.UserDao;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.User;
import org.springframework.stereotype.Service;

import static org.atomicHabit.constance.habitConst.EMAIL_ALREADY_EXIST;
import static org.atomicHabit.constance.habitConst.SUCCESS;

@Service
public class UserService {
    private UserDao userDao;

public UserService(UserDao userDao){
    this.userDao=userDao;
}

    public Result addUser(User user){
      if(userDao.existsUserByEmail(user.getEmail())){
          return new Result<>(EMAIL_ALREADY_EXIST);
      }else {
          userDao.save(user);
          return new Result<>(SUCCESS);
      }
    }
    public Result getUse( Integer id){
        User user=userDao.findById(id).get();
        return new Result<User>(SUCCESS,user);
    }
    public Result updateUser( User user, Integer id){
    user.setUserId(id);
    userDao.save(user);
    return new Result<>(SUCCESS);
    }
}
