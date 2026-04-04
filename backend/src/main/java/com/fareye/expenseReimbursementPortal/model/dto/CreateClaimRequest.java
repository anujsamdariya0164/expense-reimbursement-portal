package com.fareye.expenseReimbursementPortal.model.dto;

import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import lombok.Getter;

@Getter
public class CreateClaimRequest {
    private Long amount;

    private Claim.CATEGORIES category;

    private String comment;

    private String proofUrl;

    private Claim.APPROVAL_MODE approvalMode;

    private Long employeeId;
}
