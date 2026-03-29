package com.dukanai.webhook;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dukanai.dto.DecisionResult;
import com.dukanai.model.Message;
import com.dukanai.service.core.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WhatsAppWebhookController {

    private final MessageService messageService;

    @PostMapping(value = "/whatsapp", produces = "application/xml")
    public String receiveMessage(
            @RequestParam("From") String from,
            @RequestParam("Body") String body
    ) {

        DecisionResult result = messageService.processIncomingMessage(from, body);

        if (!result.isShouldReply()) {
            return "<Response></Response>";
        }

        return "<Response><Message>" + result.getReplyText() + "</Message></Response>";
    }

    @GetMapping("/{phone}")
    public List<Message> getAllMessages(@PathVariable String phone){
        return messageService.getMessagesByPhone(phone);
    }
}