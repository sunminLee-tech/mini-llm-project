package com.sun.llm.entity.chatbot;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_main_msg_mgmt")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainMsgMgmtEntity {

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public MainMsgMgmtEntity(String clientId){
        this.clientId = clientId;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
}
