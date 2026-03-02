package com.sun.llm.repository.chatbot;

import com.sun.llm.entity.chatbot.MsgHistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgHistRepository extends JpaRepository<MsgHistEntity, Long> {

    List<MsgHistEntity> findAllByClientIdOrderByCreatedAtAsc(String clientId);

    void deleteByClientId(String clientId);
}
