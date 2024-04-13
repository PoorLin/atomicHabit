package org.atomicHabit.dao;

import org.atomicHabit.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Users,Integer> {
    public boolean existsUserByEmailOrUserName(String email,String userName);

    public boolean existsUserByEmail(String email);

    public Users findByEmailAndSecret(String email, String secret);

    public Users findByEmail(String email);
}
