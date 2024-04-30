package org.atomicHabit.service;

import org.atomicHabit.dao.HabitDao;
import org.atomicHabit.dao.UserDao;
import org.atomicHabit.dao.WeekHabitRecord;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.Users;
import org.atomicHabit.model.dto.HabitAllsuccRateDTO;
import org.atomicHabit.util.JavaMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.atomicHabit.constance.habitConst.*;
import static org.atomicHabit.util.JavaMail.genAuthCode;
import static org.atomicHabit.util.Jwt.generateToken;
import static org.atomicHabit.util.Jwt.parseSHA256;

@Service
public class UserService {
    private UserDao userDao;

    private HabitDao habitDao;

    @Value("${jwt.key}")
    private  String jwtKey;
    @Value("${jwt.exipred}")
    private String jwtExpired;
    @Value("${java.mail}")
    private String javaSecret;


public UserService(UserDao userDao,HabitDao habitDao){
    this.userDao=userDao;
    this.habitDao=habitDao;
}

    public Result addUser(Users user){
      if(userDao.existsUserByEmailOrUserName(user.getEmail(),user.getUserName())){
          return new Result<>(EMAIL_ALREADY_EXIST);
      }else {
          String sha256Secret=parseSHA256(user.getSecret());
          user.setSecret(sha256Secret);
          Integer id=userDao.save(user).getUserId();
          userDao.flush();
          return new Result<>(SUCCESS,id);
      }
    }
    public Result getUse( Integer id){
        Users user=userDao.findById(id).get();
        return new Result<Users>(SUCCESS,user);
    }
    public Result getAllUse(){
        List<Users> usersList=userDao.findAll();
        return new Result(SUCCESS,usersList);
    }
    public Result updateUser(Users user, Integer id){
    user.setUserId(id);
    userDao.save(user);
    return new Result<>(SUCCESS);
    }

    public Result login( Users user){
        String sha256Secret=parseSHA256(user.getSecret());
        Users userRe=userDao.findByEmailAndSecret(user.getEmail(),sha256Secret);
        if(userRe == null){
            return new Result<>(USER_NOT_EXIST);
        }

        String token=generateToken(userRe.getUserName(),userRe, Long.parseLong(jwtExpired),jwtKey);
        userRe.setToken(token);
        return new Result<>(SUCCESS,userRe);
    }
    public Result loginByGoogle( String token){
        String jwtToken = token;
        String[] parts = jwtToken.split("\\.");
        String decodedHeader = new String(Base64.getDecoder().decode(parts[0]));
        String decodedPayload = new String(Base64.getDecoder().decode(parts[1]));

        return new Result<>(SUCCESS);
    }

    public Result forgotPass( Users user){
        JavaMail javaMail=new JavaMail();
        if(userDao.existsUserByEmail(user.getEmail())){
            javaMail.setPASSWORD(javaSecret);
            javaMail.setRECIPIENT(user.getEmail());
            String secret=genAuthCode();
            javaMail.setTXT("驗證碼為 :"+secret);
            javaMail.sendMail();
            return new Result<>(SUCCESS,secret);

        }else {
            return new Result<>(EMAIL_NOT_EXIST);

        }
    }

    public Result ChangePass( Users user){
         Users nowUser=userDao.findByEmail(user.getEmail());
      String newSecret = user.getSecret();
         if( nowUser!= null &&  newSecret!= null){
             String sha256Secret=parseSHA256(newSecret);
             nowUser.setSecret(sha256Secret);
            userDao.save(nowUser);
             return new Result<>(SUCCESS);
         }else {
             return new Result<>(EMAIL_NOT_EXIST);
         }
    }


    public Result findLatestWeekRecord(Integer habitId){
        List<Object[]> habitRecordList=habitDao.findLatestWeekRecord(habitId);
        List<WeekHabitRecord> recordList = new ArrayList<>();

        for (Object[] data : habitRecordList) {
            WeekHabitRecord record = new WeekHabitRecord();
            record.setRecordDate(((java.sql.Date) data[0]).toString());
            record.setHabitRecordId((BigInteger) data[1]);
            record.setHabitId((BigInteger) data[2]);
            record.setIsSuccess((BigInteger) data[3]);
            record.setStatus((BigInteger) data[4]);
            recordList.add(record);
        }
        return new Result<>(SUCCESS,recordList);
    }


    public Result getHrExistYears(Integer userId){
        List<Integer> years=habitDao.getHrExistYears(userId);
        return new Result<>(SUCCESS,years);
    }

    public Result getHrExistYearAndMonth(Integer userId){
        List<String> yearAndMonth=habitDao.getHrExistYearAndMonth(userId);
        return new Result<>(SUCCESS,yearAndMonth);
    }

    public Result findUsersHabitSuccRate(Integer userId){

        List<Object[]> objList=habitDao.countHabitRecordSuccessByUserId(userId);
        List<HabitAllsuccRateDTO> habitAllsuccRates=new ArrayList<>();
        for (Object[] data : objList) {
            HabitAllsuccRateDTO record = new HabitAllsuccRateDTO();
            record.setIs_success((BigInteger) data[0]);
            record.setSuccCount((BigInteger) data[1]);
            habitAllsuccRates.add(record);
        }
        return new Result<>(SUCCESS,habitAllsuccRates);
    }
    public Result findUsersHabitSuccRateYear(Integer userId,Integer year){
        List<Object[]> objList=habitDao.countHabitRecordSuccessByUserIdAndYear(userId,year);
        List<HabitAllsuccRateDTO> habitAllsuccRates=new ArrayList<>();
        for (Object[] data : objList) {
            HabitAllsuccRateDTO record = new HabitAllsuccRateDTO();
            record.setIs_success((BigInteger) data[0]);
            record.setSuccCount((BigInteger) data[1]);
            habitAllsuccRates.add(record);
        }
        return new Result<>(SUCCESS,habitAllsuccRates);
    }

    public Result countHabitRecordSuccessByUserIdAndYM(Integer userId,String yearAndMonth){
        List<Object[]> objList=habitDao.countHabitRecordSuccessByUserIdAndYM(userId,yearAndMonth);
        List<HabitAllsuccRateDTO> habitAllsuccRates=new ArrayList<>();
        for (Object[] data : objList) {
            HabitAllsuccRateDTO record = new HabitAllsuccRateDTO();
            record.setIs_success((BigInteger) data[0]);
            record.setSuccCount((BigInteger) data[1]);
            habitAllsuccRates.add(record);
        }
        return new Result<>(SUCCESS,habitAllsuccRates);
    }
}
