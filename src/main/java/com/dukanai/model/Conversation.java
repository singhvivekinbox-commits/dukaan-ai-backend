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
@Table(name = "conversations", indexes = {
        @Index(name = "idx_customer_phone", columnList = "customerPhone")
})
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerPhone;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
