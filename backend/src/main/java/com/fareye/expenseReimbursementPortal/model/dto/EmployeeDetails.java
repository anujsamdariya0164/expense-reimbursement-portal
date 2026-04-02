package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeDetails {
    public Long id;

    public String name;

    public String email;
}
