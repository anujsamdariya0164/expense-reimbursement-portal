package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Getter;

@Getter
public class CreateBudgetRequest {
    private Long limit;

    private Long departmentId;
}
