package org.atomicHabit.dao;

import org.atomicHabit.model.HabitGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitGroupDao extends JpaRepository<HabitGroup,Integer> {
}
