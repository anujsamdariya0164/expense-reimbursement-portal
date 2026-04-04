package com.fareye.expenseReimbursementPortal.mapper;

import com.fareye.expenseReimbursementPortal.model.dto.BudgetResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Budget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BudgetMapper {
    public BudgetResponse toBudgetResponse(Budget budget) {
        return BudgetResponse.builder()
                .id(budget.getId())
                .amount(budget.getAmount())
                .limit(budget.getLimit())
                .departmentId(budget.getDepartmentAssigned() == null ? null : budget.getDepartmentAssigned().getId())
                .departmentName(budget.getDepartmentAssigned() == null ? null : budget.getDepartmentAssigned().getName())
                .build();
    }

    public List<BudgetResponse> toListOfBudgetResponses(List<Budget> budgets) {
        return budgets.stream().map(budget ->
                toBudgetResponse(budget)
        ).toList();
    }
}
