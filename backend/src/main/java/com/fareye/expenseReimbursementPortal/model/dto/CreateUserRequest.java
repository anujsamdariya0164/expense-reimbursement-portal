package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;

    private String email;

    private Long roleId;

    private Long departmentId;
}
