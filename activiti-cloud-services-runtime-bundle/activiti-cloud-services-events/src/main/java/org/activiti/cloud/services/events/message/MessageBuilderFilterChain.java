/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.cloud.services.events.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class MessageBuilderFilterChain {

    private final List<MessageBuilderFilter> filters = new ArrayList<>();

    public <P> Message<P> build(P payload) {
        MessageBuilder<P> request = MessageBuilder.withPayload(payload);

        // Let's resolve payload class name 
        request.setHeader("messagePayloadType", payload.getClass().getName());
        
        for (MessageBuilderFilter filter : filters) {
            filter.apply(request);
        }

        return request.build();
    }

    public MessageBuilderFilterChain chain(MessageBuilderFilter filter) {
        filters.add(filter);

        return this;
    }

}
