package com.dukanai.service.ai;

import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    @Override
    public String generateReply(String message) {

        // Dummy AI logic (replace later with real AI)
        if (message.contains("fees")) {
            return "Sir ₹1500/month hai, visit karna hai?";
        }

        if (message.contains("timing")) {
            return "Gym timing morning 6am to 10am and evening 5pm to 10pm";
        }

        return "Please visit our gym for more details 😊";
    }
}