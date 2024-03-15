package org.atomicHabit.dao;

import org.atomicHabit.model.GroupMember;
import org.atomicHabit.model.embedId.GroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberDao extends JpaRepository<GroupMember, GroupMemberId> {
}
