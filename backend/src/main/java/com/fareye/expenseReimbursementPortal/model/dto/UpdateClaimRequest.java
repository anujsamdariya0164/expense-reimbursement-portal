package com.fareye.expenseReimbursementPortal.model.dto;

import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import lombok.Getter;

@Getter
public class UpdateClaimRequest {
    private Claim.STATUSES status;
}
