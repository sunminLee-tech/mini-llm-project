package com.sun.llm.entity.chatbot;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatbotEntity {

    private String clientId;
    private String message;
}
