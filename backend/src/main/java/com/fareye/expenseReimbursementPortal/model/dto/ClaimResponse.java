package com.fareye.expenseReimbursementPortal.model.dto;

import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClaimResponse {
    private Long id;

    private Long amount;

    private Claim.CATEGORIES category;

    private String comment;

    private String proofUrl;

    private Claim.STATUSES status;

    private Claim.APPROVAL_MODE approvalMode;

    private Long budgetId;

    private Long employeeId;

    private String employeeName;

    private String employeeEmail;
}
