package com.sun.llm.repository.chatbot;

import com.sun.llm.entity.chatbot.MainMsgMgmtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainMsgMgmtRepository extends JpaRepository<MainMsgMgmtEntity, String> {
}
