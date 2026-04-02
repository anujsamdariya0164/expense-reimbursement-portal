package com.fareye.expenseReimbursementPortal.mapper;

import com.fareye.expenseReimbursementPortal.model.dto.DepartmentResponse;
import com.fareye.expenseReimbursementPortal.model.dto.EmployeeDetails;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentMapper {
    public DepartmentResponse toDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .managerId(department.getManager() == null ? null : department.getManager().getId())
                .managerName(department.getManager() == null ? null : department.getManager().getName())
                .managerEmail(department.getManager() == null ? null : department.getManager().getEmail())
                .budgetId(department.getBudget() == null ? null : department.getBudget().getId())
                .employees(department.getEmployees() == null ? null :
                        department.getEmployees().stream().map(employee ->
                                                               EmployeeDetails.builder()
                                                               .id(employee.getId())
                                                               .name(employee.getName())
                                                               .email(employee.getEmail())
                                                               .build()
                        ).toList()
                )
                .build();
    }

    public List<DepartmentResponse> toListOfDepartmentResponses(List<Department> departments) {
        return departments.stream().map(department ->
                toDepartmentResponse(department)
        ).toList();
    }
}
