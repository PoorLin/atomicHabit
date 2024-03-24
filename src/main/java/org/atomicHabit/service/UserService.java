package org.atomicHabit.service;

import com.nimbusds.jose.jwk.JWKSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.atomicHabit.dao.UserDao;
import org.atomicHabit.model.Habit;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.User;
import org.atomicHabit.util.JavaMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.Key;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Optional;

import static org.atomicHabit.constance.habitConst.*;
import static org.atomicHabit.util.JavaMail.genAuthCode;
import static org.atomicHabit.util.Jwt.generateToken;
import static org.atomicHabit.util.Jwt.parseSHA256;

@Service
public class UserService {
    private UserDao userDao;
    @Value("${jwt.key}")
    private  String jwtKey;
    @Value("${jwt.exipred}")
    private String jwtExpired;
    @Value("${java.mail}")
    private String javaSecret;


public UserService(UserDao userDao){
    this.userDao=userDao;
}

    public Result addUser(User user){
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
        User user=userDao.findById(id).get();
        return new Result<User>(SUCCESS,user);
    }
    public Result updateUser( User user, Integer id){
    user.setUserId(id);
    userDao.save(user);
    return new Result<>(SUCCESS);
    }

    public Result login( User user){
        String sha256Secret=parseSHA256(user.getSecret());
        User userRe=userDao.findByEmailAndSecret(user.getEmail(),sha256Secret);
        if(userRe == null){
            return new Result<>(USER_NOT_EXIST);
        }
        String token=generateToken(userRe.getUserName(),userRe, Long.parseLong(jwtExpired),jwtKey);
        userRe.setToken(token);
        System.out.println(token);
        return new Result<>(SUCCESS,userRe);
    }
    public Result loginByGoogle( String token){
        String jwtToken = token;
        String[] parts = jwtToken.split("\\.");
        String decodedHeader = new String(Base64.getDecoder().decode(parts[0]));
        String decodedPayload = new String(Base64.getDecoder().decode(parts[1]));

        return new Result<>(SUCCESS);
    }

    public Result forgotPass( User user){
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

    public Result ChangePass( User user){
         User nowUser=userDao.findByEmail(user.getEmail());
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

}
