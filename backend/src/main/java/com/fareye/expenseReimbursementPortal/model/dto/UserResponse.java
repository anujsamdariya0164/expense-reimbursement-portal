package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserResponse {
    private Long id;

    private String email;

    private String name;

    private Long roleId;

    private String role;

    private Long departmentId;

    private String departmentName;

    private Long managerId;

    private String managerName;

    private String managerEmail;

    private Long budgetAmount;
}
