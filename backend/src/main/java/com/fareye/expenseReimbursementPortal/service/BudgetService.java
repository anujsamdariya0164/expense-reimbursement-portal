package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.exception.DatabaseConnectionException;
import com.fareye.expenseReimbursementPortal.mapper.BudgetMapper;
import com.fareye.expenseReimbursementPortal.model.dto.BudgetResponse;
import com.fareye.expenseReimbursementPortal.model.dto.CreateBudgetRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UpdateBudgetRequest;
import com.fareye.expenseReimbursementPortal.model.entity.Budget;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import com.fareye.expenseReimbursementPortal.repository.BudgetRepository;
import com.fareye.expenseReimbursementPortal.repository.DepartmentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;

    private final BudgetMapper budgetMapper;

    private final DepartmentRepository departmentRepository;

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper, DepartmentRepository departmentRepository) {
        this.budgetRepository = budgetRepository;

        this.budgetMapper = budgetMapper;

        this.departmentRepository = departmentRepository;
    }

    public List<BudgetResponse> getAllBudgets() {
        try {
            return budgetMapper.toListOfBudgetResponses(budgetRepository.findAll());
        } catch (DataAccessException e) {
            throw new DatabaseConnectionException("Database connection error!");
        }
    }

    public BudgetResponse getBudgetById(Long id) {
        return budgetMapper.toBudgetResponse(budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget with ID: " + id + " does not exists!")));
    }

    public BudgetResponse createBudget(CreateBudgetRequest createBudgetRequest) {
        Department departmentById = departmentRepository.findById(createBudgetRequest.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department with ID: " + createBudgetRequest.getDepartmentId() + " does not exists!"));

        if (departmentById.getBudget() != null) {
            throw new RuntimeException("Department with ID: " + createBudgetRequest.getDepartmentId() + " already has a budget assigned to it!");
        }

        Budget newBudget = Budget.builder()
                .limit(createBudgetRequest.getLimit())
                .amount(0L)
                .departmentAssigned(departmentById)
                .claims(new ArrayList<>())
                .build();

        return budgetMapper.toBudgetResponse(budgetRepository.save(newBudget));
    }

    public BudgetResponse updateBudgetById(Long id, UpdateBudgetRequest updateBudgetRequest) {
        Budget budgetById = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget with ID: " + id + " does not exists!"));

        if (budgetById.getAmount() + updateBudgetRequest.getAmount() > budgetById.getLimit()) {
            throw new RuntimeException("Cannot cross limit!");
        }

        budgetById.setAmount(budgetById.getAmount() + updateBudgetRequest.getAmount());

        return budgetMapper.toBudgetResponse(budgetRepository.save(budgetById));
    }

    public void deleteBudgetById(Long id) {
        budgetRepository.deleteById(id);
    }
}
