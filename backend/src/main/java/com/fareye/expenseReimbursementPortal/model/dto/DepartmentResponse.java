package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DepartmentResponse {
    private Long id;

    private String name;

    private Long managerId;

    private String managerName;

    private String managerEmail;

    private Long budgetId;

    private List<EmployeeDetails> employees;
}
