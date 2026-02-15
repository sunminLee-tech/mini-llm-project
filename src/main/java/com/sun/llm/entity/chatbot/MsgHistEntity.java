package com.sun.llm.entity.chatbot;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_msg_hist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MsgHistEntity {

    public enum Role {
        USER, ASSISTANT
    }

    public enum MsgStatus {
        PENDING, DONE, FAIL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MsgStatus status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    private MsgHistEntity(String clientId, Role role, MsgStatus status, String content) {
        this.clientId = clientId;
        this.role = role;
        this.status = status;
        this.content = content;
    }

    public static MsgHistEntity createUserMessage(String clientId, String content) {
        return MsgHistEntity.builder()
                .clientId(clientId)
                .role(Role.USER)
                .status(MsgStatus.PENDING)
                .content(content)
                .build();
    }

    public static MsgHistEntity createAssistantMessage(String clientId, String content) {
        return MsgHistEntity.builder()
                .clientId(clientId)
                .role(Role.ASSISTANT)
                .status(MsgStatus.DONE)
                .content(content)
                .build();
    }
}
