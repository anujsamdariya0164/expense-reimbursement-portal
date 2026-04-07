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
                .roleId(user.getRole().getId())
                .role(user.getRole().getRole())
                .departmentId(user.getDepartment() == null ? null: user.getDepartment().getId())
                .departmentName(user.getDepartment() == null ? null: user.getDepartment().getName())
                .managerId(user.getManager() == null ? null : user.getManager().getId())
                .managerName(user.getManager() == null ? null : user.getManager().getName())
                .managerEmail(user.getManager() == null ? null : user.getManager().getEmail())
                .budgetAmount(user.getDepartment() == null ? null: user.getDepartment().getBudget() == null ? null: user.getDepartment().getBudget().getAmount())
                .build();
    }

    public List<UserResponse> toListOfUserResponse(List<User> users) {
        return users.stream().map(user -> toUserResponse(user)).toList();
    }
}
