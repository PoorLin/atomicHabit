package org.atomicHabit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public Users() {
    }

    public Users(String userName, String email, Integer sex, String secret) {
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.secret = secret;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(nullable = false)
    private Integer sex;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", secret='" + secret + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Column(nullable = false)
    private String secret;


    private String token;


}
