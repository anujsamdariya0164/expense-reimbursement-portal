package com.fareye.expenseReimbursementPortal.model.dto;

import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AuditLogResponse {
    private Long id;

    private Claim.STATUSES action;

    private LocalDateTime timestamp;

    private Long actorId;

    private String actorName;

    private String actorEmail;

    private String role;

    private Long claimId;
}
