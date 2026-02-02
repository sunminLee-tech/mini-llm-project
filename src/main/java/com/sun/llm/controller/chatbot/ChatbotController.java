package com.sun.llm.controller.chatbot;

import com.sun.llm.common.ApiResponse;
import com.sun.llm.dto.chatbot.request.ChatbotRequestDTO;
import com.sun.llm.entity.chatbot.ChatResponse;
import com.sun.llm.service.chatbot.ChatbotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/chatbot")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> askChatbot(@RequestBody ChatbotRequestDTO request) {
        ApiResponse<String> response = chatbotService.askChatbot(request.from());
        return ResponseEntity.ok(response);
    }


}
