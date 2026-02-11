package com.sun.llm.entity.chatbot;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class ChatbotEntity {

    private String clientId;
    private String message;
    private boolean isNew;
}
