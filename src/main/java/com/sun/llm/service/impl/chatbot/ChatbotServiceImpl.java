package com.sun.llm.service.impl.chatbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.llm.common.ApiResponse;
import com.sun.llm.entity.chatbot.ChatbotEntity;
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

    public ChatbotServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
            String jsonBody = objectMapper.writeValueAsString(request);
            log.info("Sending JSON: {}", jsonBody);

            String response = restClient.post()
                    .uri("/chat")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(jsonBody)
                    .retrieve()
                    .body(String.class);

            return ApiResponse.success(response);
        } catch (Exception e) {
            log.error("Error calling chatbot: ", e);
            return ApiResponse.failure("Failed to call chatbot");
        }
    }
}

