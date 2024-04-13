package org.atomicHabit.dao;

import org.atomicHabit.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class UsersDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void existsUserByEmailOrUserName() {
       //given
        String email="123@gmail.com";
        String userName = "jack";
        Users user=new Users(
                userName,
                email,
                0,
                "123456"
        );

        given(userDao.existsUserByEmail(email))
                .willReturn(true);   //預設會回傳false
       //userDao.save(user);
        //when
        boolean expected=userDao.existsUserByEmailOrUserName(email,userName);
        //then
        assertThat(expected).isTrue();




    }

    @Test
    void existsUserByEmail() {
    }

    @Test
    void findByEmailAndSecret() {
    }

    @Test
    void findByEmail() {
    }
}