package com.fareye.expenseReimbursementPortal.model.dto;

import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateAuditLogRequest {
    private Claim.STATUSES action;

    private Long actorId;

    private Long claimId;
}
