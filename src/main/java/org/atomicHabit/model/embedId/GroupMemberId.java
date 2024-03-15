package org.atomicHabit.model.embedId;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GroupMemberId implements Serializable {


    public GroupMemberId() {
    }

    private Integer groupId;

    private Integer userId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
