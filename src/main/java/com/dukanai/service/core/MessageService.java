package com.dukanai.service.core;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dukanai.dto.DecisionResult;
import com.dukanai.model.Conversation;
import com.dukanai.model.Lead;
import com.dukanai.model.Message;
import com.dukanai.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    private final LeadService leadService;
    private final DecisionService decisionService;

    public DecisionResult processIncomingMessage(String from, String text) {

        // ✅ Step 1: Normalize phone (Twilio format fix)
        String normalizedPhone = from.replace("whatsapp:", "").trim();

        // ✅ Step 2: Get/Create conversation
        Conversation conversation = conversationService.getOrCreateConversation(normalizedPhone);

        // ✅ Step 3: Save CUSTOMER message
        Message customerMessage = Message.builder()
                .conversationId(conversation.getId())
                .customerPhone(normalizedPhone)
                .messageText(text)
                .sender("CUSTOMER")
                .timestamp(LocalDateTime.now())
                .build();

        messageRepository.save(customerMessage);

        // ✅ Step 4: Update conversation timestamp
        conversation.setUpdatedAt(LocalDateTime.now());

        // ✅ Step 5: Handle lead
        Lead lead = leadService.getOrCreateLead(normalizedPhone);
        leadService.updateLeadActivity(lead);

        // ✅ Step 6: Decision logic
        DecisionResult decision = decisionService.decide(customerMessage, lead);

        // ✅ Step 7: Save BOT reply
        if (decision.isShouldReply()) {

            Message botMessage = Message.builder()
                    .conversationId(conversation.getId())
                    .customerPhone(normalizedPhone)
                    .messageText(decision.getReplyText())
                    .sender("BOT")
                    .timestamp(LocalDateTime.now())
                    .build();

            messageRepository.save(botMessage);
        }

        // ✅ Step 8: Handle escalation
        if (decision.isEscalateToHuman()) {
            lead.setStatus("ESCALATED");
            leadService.updateLeadActivity(lead);
        }

        return decision;
    }

    public List<Message> getMessagesByPhone(String phone) {
        return messageRepository.findByCustomerPhoneOrderByTimestampDesc(phone);
    }
}