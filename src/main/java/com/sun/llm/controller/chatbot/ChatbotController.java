package com.sun.llm.controller.chatbot;

import com.sun.llm.common.ApiResponse;
import com.sun.llm.dto.chatbot.request.ChatbotRequestDTO;
import com.sun.llm.entity.chatbot.ChatResponse;
import com.sun.llm.entity.chatbot.MainMsgMgmtEntity;
import com.sun.llm.entity.chatbot.MsgHistEntity;
import com.sun.llm.service.chatbot.ChatbotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<MainMsgMgmtEntity>>> historyChatbot() {
        ApiResponse<List<MainMsgMgmtEntity>> response = chatbotService.historyChatbot();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/messages")
    public ResponseEntity<ApiResponse<List<MsgHistEntity>>> inquiryMsgOne(@RequestParam(name="client_id") String clientId) {
        ApiResponse<List<MsgHistEntity>> response = chatbotService.inquiryMsgOne(clientId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/history/{clientId}")
    public ResponseEntity<ApiResponse> delHistoryChatbot(@PathVariable String clientId) {
        ApiResponse<List<MainMsgMgmtEntity>> response = chatbotService.delHistoryChatbot(clientId);
        return ResponseEntity.ok(response);
    }


}
