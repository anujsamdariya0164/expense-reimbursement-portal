package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;

    private String email;

    private String password;

    private Long roleId;

    private Long managerId;

    private Long departmentId;
}
