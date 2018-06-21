

package com.ananda.model.event.source;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
//import lombok.Value;

//@Value
@JsonTypeName("group")
public class GroupSource implements Source {
    private final String groupId;
    private final String userId;

    /**
     * Create new instance.
     * @param groupId group ID
     * @param userId user id may be null
     */
    @JsonCreator
    public GroupSource(
            final String groupId,
            final String userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String getUserId() {
       return userId;
    }

    @Override
    public String getSenderId() {
        return groupId;
    }
}
