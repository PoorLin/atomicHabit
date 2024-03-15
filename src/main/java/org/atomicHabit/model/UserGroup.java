package org.atomicHabit.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userGroupId;
    @Column(nullable = false)
    private String groupName;

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTarget() {
        return groupTarget;
    }

    public void setGroupTarget(String groupTarget) {
        this.groupTarget = groupTarget;
    }

    public UserGroup() {
    }

    @Column(nullable = false)
    private String groupTarget;


}
