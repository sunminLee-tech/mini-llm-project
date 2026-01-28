package com.sun.llm.service.impl.chatbot;

import com.sun.llm.common.ApiResponse;
import com.sun.llm.entity.chatbot.ChatbotEntity;
import com.sun.llm.service.chatbot.ChatbotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class ChatbotServiceImpl implements ChatbotService {

    @Override
    public ApiResponse<String> askChatbot(ChatbotEntity from) {
        return null;
    }
}
