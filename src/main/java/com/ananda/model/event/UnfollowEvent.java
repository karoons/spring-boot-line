
package com.ananda.model.event;

import com.ananda.model.event.source.Source;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.Instant;

/**
 * Event object for when your account is blocked.
 */
//@Value
@JsonTypeName("unfollow")
public class UnfollowEvent implements Event {
    /**
     * JSON object which contains the source of the event
     */
    private final Source source;

    /**
     * Time of the event
     */
    private final Instant timestamp;

    @JsonCreator
    public UnfollowEvent(
            final Source source,
            final Instant timestamp) {
        this.source = source;
        this.timestamp = timestamp;
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }
}
