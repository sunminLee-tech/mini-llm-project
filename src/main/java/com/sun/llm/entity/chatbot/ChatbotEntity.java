package com.sun.llm.entity.chatbot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotEntity {

    String clientId;
    String message;
}
