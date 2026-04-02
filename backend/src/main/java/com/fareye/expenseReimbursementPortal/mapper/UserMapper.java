package com.fareye.expenseReimbursementPortal.mapper;

import com.fareye.expenseReimbursementPortal.model.dto.EmployeeDetails;
import com.fareye.expenseReimbursementPortal.model.dto.UserResponse;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().getRole())
                .departmentId(user.getDepartment() == null ? null: user.getDepartment().getId())
                .departmentName(user.getDepartment() == null ? null: user.getDepartment().getName())
                .managerId(user.getManager() == null ? null : user.getManager().getId())
                .managerName(user.getManager() == null ? null : user.getManager().getName())
                .managerEmail(user.getManager() == null ? null : user.getManager().getEmail())
                .employees(
                        user.getEmployees() == null ? null : user.getEmployees().stream().map(employee ->
                                EmployeeDetails.builder()
                                        .id(employee.getId())
                                        .name(employee.getName())
                                        .email(employee.getEmail())
                                        .build()
                        ).toList()
                )
                .build();
    }

    public List<UserResponse> toListOfUserResponse(List<User> users) {
        return users.stream().map(user -> toUserResponse(user)).toList();
    }
}
