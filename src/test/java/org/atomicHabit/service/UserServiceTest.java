package org.atomicHabit.service;

import org.atomicHabit.dao.UserDao;
import org.atomicHabit.model.Users;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

//    private  AutoCloseable autoCloseable;   有了 @ExtendWith(MockitoExtension.class) 就不需要自己建立
    private UserService userServiceTest;


    @BeforeEach  //測試開始前會執行
    void setUp() {
//        autoCloseable =MockitoAnnotations.openMocks(this);
        userServiceTest = new UserService(userDao);

    }

//    @AfterEach
//     void afterEach() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void CanAddUser() {
        //given
        String email="123@gmail.com";
        String userName = "jack";
        Users user=new Users(
                userName,
                email,
                0,
                "123456"
        );
        user.setUserId(1);
        given(userDao.save(user))

                .willReturn(user);
        //when
        userServiceTest.addUser(user);
        //then
        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        verify(userDao).save(usersArgumentCaptor.capture()); //會去抓取在service執行save那行的users的物件
        Users users=usersArgumentCaptor.getValue();
        assertThat(users).isEqualTo(user);

        assertThatThrownBy(()-> userDao.save(user))
                .isInstanceOf(Exception.class)
                        .hasMessage("Email");
         verify(userDao,never()).save(any()); //若這個save沒被call到 但是我前面有呼叫到 ，故此會報錯，這用於一個service 多種使用案例

    }
    @Test
    void CanFindAllUser() {
        //when
     userServiceTest.getAllUse();
        //then
     verify(userDao).findAll();
    }
}