package com.dukanai.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecisionResult {

    private boolean shouldReply;
    private String replyText;
    private boolean escalateToHuman;
}