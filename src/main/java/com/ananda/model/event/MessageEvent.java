/*
 * Copyright 2018 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ananda.model.event;

import com.ananda.model.event.message.MessageContent;
import com.ananda.model.event.source.Source;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.Instant;

/**
 * Event object which contains the sent message.
 * The message field contains a message object which corresponds with the message type. You can reply to message events.
 */
//@Value
@JsonTypeName("message")
public class MessageEvent<T extends MessageContent> implements Event, ReplyEvent {
    /**
     * Token for replying to this event
     */
    private final String replyToken;

    private final String type;

    /**
     * JSON object which contains the source of the event
     */
    private final Source source;

    /**
     * Message body
     */
    private final T message;


    /**
     * Time of the event
     */
    private final Instant timestamp;

    @JsonCreator
    public MessageEvent(
            final String replyToken,
            final String type,
            final Source source,
            final T message,
            final Instant timestamp) {
        this.replyToken = replyToken;
        this.source = source;
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String getReplyToken() {
        return replyToken;
    }
}
