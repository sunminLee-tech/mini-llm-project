package com.sun.llm.service.impl.chatbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.llm.common.ApiResponse;
import com.sun.llm.entity.chatbot.ChatResponse;
import com.sun.llm.entity.chatbot.ChatbotEntity;
import com.sun.llm.entity.chatbot.MainMsgMgmtEntity;
import com.sun.llm.entity.chatbot.MsgHistEntity;
import com.sun.llm.repository.chatbot.MainMsgMgmtRepository;
import com.sun.llm.repository.chatbot.MsgHistRepository;
import com.sun.llm.service.chatbot.ChatbotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class ChatbotServiceImpl implements ChatbotService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final MainMsgMgmtRepository mainMsgMgmtRepository;
    private final MsgHistRepository msgHistRepository;

    public ChatbotServiceImpl(ObjectMapper objectMapper, MainMsgMgmtRepository mainMsgMgmtRepository, MsgHistRepository msgHistRepository) {
        this.objectMapper = objectMapper;
        this.mainMsgMgmtRepository = mainMsgMgmtRepository;
        this.msgHistRepository = msgHistRepository;
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8000")
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .build();
    }

//    private ClientHttpRequestInterceptor loggingInterceptor() {
//        return (request, body, execution) -> {
//            log.info("====== HTTP Request ======");
//            log.info("URL: {}", request.getURI());
//            log.info("Method: {}", request.getMethod());
//            log.info("Headers: {}", request.getHeaders());
//            log.info("Body: {}", new String(body, StandardCharsets.UTF_8));
//            log.info("==========================");
//            return execution.execute(request, body);
//        };
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse<String> askChatbot(ChatbotEntity request) {
        try {

            if (!mainMsgMgmtRepository.existsById(request.getClientId())) {
                MainMsgMgmtEntity mainMsgMgmtEntity = MainMsgMgmtEntity.builder()
                        .clientId(request.getClientId())
                        .build();
                mainMsgMgmtRepository.save(mainMsgMgmtEntity);
                request.setNew(true);
            }

            MsgHistEntity msgHistEntity = MsgHistEntity.createUserMessage(request.getClientId(), request.getMessage());

            MsgHistEntity savedMsgHist = msgHistRepository.save(msgHistEntity);
            log.info("savedMsgHist seq: {}", savedMsgHist.getSeq());

            String jsonBody = objectMapper.writeValueAsString(request);
            log.info("Sending JSON: {}", jsonBody);

            ChatResponse response = restClient.post()
                    .uri("/chat")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(jsonBody)
                    .retrieve()
                    .body(ChatResponse.class);

            if (response == null) {
                return ApiResponse.failure("Empty response from chatbot");
            }
            MsgHistEntity responseEntity = MsgHistEntity.createAssistantMessage(request.getClientId(), response.getMessage());
            msgHistRepository.save(responseEntity);

            if (request.isNew()) {
                MainMsgMgmtEntity mainEntity = mainMsgMgmtRepository.findById(request.getClientId()).orElseThrow();
                mainEntity.updateTitle(response.getTitle());
            }

            log.info("response =========================>{}", response);
            return ApiResponse.success(response.getMessage());
        } catch (Exception e) {
            log.error("Error calling chatbot: ", e);
            return ApiResponse.failure("Failed to call chatbot");
        }
    }
}