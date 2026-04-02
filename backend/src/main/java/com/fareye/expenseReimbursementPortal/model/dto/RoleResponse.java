package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RoleResponse {
    private Long id;

    private String role;

    private List<EmployeeDetails> users;
}
