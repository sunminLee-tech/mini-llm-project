package com.sun.llm.repository.chatbot;

import com.sun.llm.entity.chatbot.MsgHistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgHistRepository extends JpaRepository<MsgHistEntity, Long> {
}
