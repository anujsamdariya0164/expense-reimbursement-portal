package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateBudgetRequest {
    private Long amount;
}
