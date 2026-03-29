package com.dukanai.service.core;

import com.dukanai.model.Conversation;
import com.dukanai.repository.ConversationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public Conversation getOrCreateConversation(String phone) {

        return conversationRepository.findByCustomerPhone(phone)
                .map(convo -> {
                    convo.setUpdatedAt(LocalDateTime.now());
                    return conversationRepository.save(convo);
                })
                .orElseGet(() -> {
                    Conversation convo = Conversation.builder()
                            .customerPhone(phone)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();

                    return conversationRepository.save(convo);
                });
    }
}