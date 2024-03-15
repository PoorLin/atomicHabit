package org.atomicHabit.dao;

import org.atomicHabit.model.HabitGroupRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitGRecordDao extends JpaRepository<HabitGroupRecord,Integer> {
}
