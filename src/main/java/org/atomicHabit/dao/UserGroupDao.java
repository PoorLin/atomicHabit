package org.atomicHabit.dao;

import org.atomicHabit.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupDao extends JpaRepository<UserGroup,Integer> {
}
