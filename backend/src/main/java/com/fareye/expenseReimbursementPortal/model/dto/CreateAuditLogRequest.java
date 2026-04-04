package com.fareye.expenseReimbursementPortal.model.dto;

import com.fareye.expenseReimbursementPortal.model.entity.AuditLog;
import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateAuditLogRequest {
    private Claim.STATUSES action;

    private Long actorId;

    private Long claimId;
}
