package com.dukanai.model;

import java.time.LocalDateTime;

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
@Table(name = "leads", indexes = {
        @Index(name = "idx_lead_phone", columnList = "customerPhone")
})
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerPhone;

    private String name;

    private String status; // NEW / HOT / COLD

    private LocalDateTime lastInteractionAt;

    private LocalDateTime createdAt;
}
