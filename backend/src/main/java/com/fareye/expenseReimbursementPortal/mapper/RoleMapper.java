package com.fareye.expenseReimbursementPortal.mapper;

import com.fareye.expenseReimbursementPortal.model.dto.EmployeeDetails;
import com.fareye.expenseReimbursementPortal.model.dto.RoleResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper {
    public RoleResponse toRoleResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getRole())
                .users(role.getUsers() == null ? null :
                        role.getUsers().stream().map(user ->
                                                     EmployeeDetails.builder()
                                                     .id(user.getId())
                                                     .name(user.getName())
                                                     .email(user.getEmail())
                                                     .build()
                        ).toList()
                )
                .build();
    }

    public List<RoleResponse> toListOfRoleResponses(List<Role> roles) {
        return roles.stream().map(role ->
                toRoleResponse(role)
        ).toList();
    }
}
