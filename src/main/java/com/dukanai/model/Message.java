package com.dukanai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "messages", indexes = {
        @Index(name = "idx_conversation_id", columnList = "conversationId"),
        @Index(name = "idx_customer_phone_msg", columnList = "customerPhone")
})
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long conversationId;

    private String customerPhone;

    @Column(columnDefinition = "TEXT")
    private String messageText;

    private String sender; // CUSTOMER / BOT / USER

    private LocalDateTime timestamp;
}