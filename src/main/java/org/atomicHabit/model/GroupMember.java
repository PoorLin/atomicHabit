package org.atomicHabit.model;

import org.atomicHabit.model.embedId.GroupMemberId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GroupMember implements Serializable {

 @EmbeddedId
 private GroupMemberId groupMemberId;

    
}
