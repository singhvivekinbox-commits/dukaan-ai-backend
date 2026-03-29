package com.dukanai.service.core;

import com.dukanai.model.Lead;
import com.dukanai.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;

    public Lead getOrCreateLead(String phone) {

        return leadRepository.findByCustomerPhone(phone)
                .orElseGet(() -> {
                    Lead lead = Lead.builder()
                            .customerPhone(phone)
                            .status("NEW")
                            .createdAt(LocalDateTime.now())
                            .lastInteractionAt(LocalDateTime.now())
                            .build();

                    return leadRepository.save(lead);
                });
    }

    public void updateLeadActivity(Lead lead) {

        lead.setLastInteractionAt(LocalDateTime.now());

        // Simple logic: NEW → HOT
        if ("NEW".equals(lead.getStatus())) {
            lead.setStatus("HOT");
        }

        leadRepository.save(lead);
    }
}