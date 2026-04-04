package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BudgetResponse {
    private Long id;

    private Long amount;

    private Long limit;

    private Long departmentId;

    private String departmentName;
}
