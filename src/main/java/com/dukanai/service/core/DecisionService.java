package com.dukanai.service.core;

import com.dukanai.dto.DecisionResult;
import com.dukanai.model.Lead;
import com.dukanai.model.Message;
import com.dukanai.service.ai.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecisionService {

    private final AIService aiService;

    public DecisionResult decide(Message message, Lead lead) {

        String text = message.getMessageText().toLowerCase().trim();

        // 🔹 Rule 1: Greetings
        if (text.contains("hi") || text.contains("hello")) {
            return DecisionResult.builder()
                    .shouldReply(true)
                    .replyText("Hello! How can I help you?")
                    .escalateToHuman(false)
                    .build();
        }

        // 🔹 Rule 2: Low intent
        if (text.equals("ok") || text.equals("thanks")) {
            return DecisionResult.builder()
                    .shouldReply(false)
                    .replyText(null)
                    .escalateToHuman(false)
                    .build();
        }

        // 🔹 Rule 3: Complex queries → human
        if (text.contains("discount") || text.contains("best price")) {
            return DecisionResult.builder()
                    .shouldReply(false)
                    .replyText(null)
                    .escalateToHuman(true)
                    .build();
        }

        // 🔹 Rule 4: Default → AI
        String aiReply = aiService.generateReply(text);

        return DecisionResult.builder()
                .shouldReply(true)
                .replyText(aiReply)
                .escalateToHuman(false)
                .build();
    }
}