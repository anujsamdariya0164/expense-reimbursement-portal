package com.fareye.expenseReimbursementPortal.controller;

import com.fareye.expenseReimbursementPortal.model.dto.BudgetResponse;
import com.fareye.expenseReimbursementPortal.model.dto.CreateBudgetRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UpdateBudgetRequest;
import com.fareye.expenseReimbursementPortal.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BudgetResponse>> getAllBudgets() {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.getAllBudgets());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BudgetResponse> getBudgetById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.getBudgetById(Long.parseLong(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody CreateBudgetRequest createBudgetRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(createBudgetRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BudgetResponse> updateBudgetBydId(@PathVariable String id, @RequestBody UpdateBudgetRequest updateBudgetRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.updateBudgetById(Long.parseLong(id), updateBudgetRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBudgetById(@PathVariable String id) {
        budgetService.deleteBudgetById(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
