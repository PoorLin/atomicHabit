package org.atomicHabit.service;

import com.nimbusds.jose.jwk.JWKSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.atomicHabit.dao.UserDao;
import org.atomicHabit.model.Habit;
import org.atomicHabit.model.Result;
import org.atomicHabit.model.User;
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

import static org.atomicHabit.constance.habitConst.*;
import static org.atomicHabit.util.Jwt.generateToken;
import static org.atomicHabit.util.Jwt.parseSHA256;

@Service
public class UserService {
    private UserDao userDao;
    @Value("${jwt.key}")
    private  String jwtKey;
    @Value("${jwt.exipred}")
    private String jwtExpired;


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
//        RestTemplate restTemplate = new RestTemplate();
//        String jwkSetUri = "https://www.googleapis.com/oauth2/v3/certs";
//        ResponseEntity<JWKSet> responseEntity = restTemplate.getForEntity(jwkSetUri, JWKSet.class);
//        JWKSet jwkSet = responseEntity.getBody();
        String jwtToken = token;
        String[] parts = jwtToken.split("\\.");

        String decodedHeader = new String(Base64.getDecoder().decode(parts[0]));
        String decodedPayload = new String(Base64.getDecoder().decode(parts[1]));

        System.out.println("Decoded Header: " + decodedHeader);
        System.out.println("Decoded Payload: " + decodedPayload);
        return new Result<>(SUCCESS);
    }
}
