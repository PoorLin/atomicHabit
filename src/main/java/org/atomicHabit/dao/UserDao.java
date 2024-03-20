package org.atomicHabit.dao;

import org.atomicHabit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    public boolean existsUserByEmailOrUserName(String email,String userName);

    public  User findByEmailAndSecret(String email,String secret);
}
