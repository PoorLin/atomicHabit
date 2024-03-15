package org.atomicHabit.dao;

import org.atomicHabit.model.HabitRecord;
import org.atomicHabit.model.embedId.HabitRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRecordDao extends JpaRepository<HabitRecord, HabitRecordId> {



}
