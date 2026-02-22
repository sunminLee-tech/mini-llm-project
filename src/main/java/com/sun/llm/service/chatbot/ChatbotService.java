package com.sun.llm.service.chatbot;

import com.sun.llm.common.ApiResponse;
import com.sun.llm.entity.chatbot.ChatbotEntity;
import com.sun.llm.entity.chatbot.MainMsgMgmtEntity;
import com.sun.llm.entity.chatbot.MsgHistEntity;

import java.util.List;

public interface ChatbotService {
    ApiResponse<String> askChatbot(ChatbotEntity from);

    ApiResponse<List<MainMsgMgmtEntity>> historyChatbot();

    ApiResponse<List<MsgHistEntity>> inquiryMsgOne(String clientId);
}
