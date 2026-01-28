package com.sun.llm.dto.chatbot.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.llm.entity.chatbot.ChatbotEntity;


public record ChatbotRequestDTO(
        @JsonProperty("client_id")
        String clientId,
        String message
) {

    public ChatbotEntity from() {
        return ChatbotEntity.builder()
                .clientId(clientId)
                .message(message)
                .build();
    }
}
