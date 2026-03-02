package com.sun.llm.repository.chatbot;

import com.sun.llm.entity.chatbot.MainMsgMgmtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainMsgMgmtRepository extends JpaRepository<MainMsgMgmtEntity, String> {

    List<MainMsgMgmtEntity> findAllByOrderByCreatedAtDesc();

    void deleteByClientId(String clientId);
}
