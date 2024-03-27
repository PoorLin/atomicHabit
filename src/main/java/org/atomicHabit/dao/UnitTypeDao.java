package org.atomicHabit.dao;

import org.atomicHabit.model.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTypeDao extends JpaRepository<UnitType,Integer> {
}
