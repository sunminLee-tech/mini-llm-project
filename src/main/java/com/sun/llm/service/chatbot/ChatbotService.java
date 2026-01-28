package com.sun.llm.service.chatbot;

import com.sun.llm.common.ApiResponse;
import com.sun.llm.entity.chatbot.ChatbotEntity;

public interface ChatbotService {
    ApiResponse<String> askChatbot(ChatbotEntity from);
}
