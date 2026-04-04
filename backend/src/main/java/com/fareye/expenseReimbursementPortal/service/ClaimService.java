package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.mapper.ClaimMapper;
import com.fareye.expenseReimbursementPortal.model.dto.CreateClaimRequest;
import com.fareye.expenseReimbursementPortal.model.dto.ClaimResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Budget;
import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import com.fareye.expenseReimbursementPortal.repository.BudgetRepository;
import com.fareye.expenseReimbursementPortal.repository.ClaimRepository;
import com.fareye.expenseReimbursementPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    private final ClaimMapper claimMapper;

    private final BudgetRepository budgetRepository;

    private final UserRepository userRepository;

    public ClaimService(ClaimRepository claimRepository, ClaimMapper claimMapper, BudgetRepository budgetRepository, UserRepository userRepository) {
        this.claimRepository = claimRepository;

        this.claimMapper = claimMapper;

        this.budgetRepository = budgetRepository;

        this.userRepository = userRepository;
    }

    public List<ClaimResponse> getAllClaims() {
        return claimMapper.toListOfClaimResponse(claimRepository.findAll());
    }

    public ClaimResponse getClaimById(Long id) {
        return claimMapper.toClaimResponse(claimRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim with ID: " + id + " does not exists!")));
    }

    public ClaimResponse createClaim(CreateClaimRequest createClaimRequest) {
        Budget budgetById = null;
        if (createClaimRequest.getBudgetId() != null) {
            budgetById = budgetRepository.findById(createClaimRequest.getBudgetId()).orElseThrow(() -> new RuntimeException("Budget with ID: " + createClaimRequest.getBudgetId() + " does not exists!"));
        }

        User employeeById = null;
        if (createClaimRequest.getEmployeeId() != null) {
            employeeById = userRepository.findById(createClaimRequest.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee with ID: " + createClaimRequest.getEmployeeId() + " does not exists!"));
        }

        Claim newClaim = Claim.builder()
                .amount(createClaimRequest.getAmount())
                .proofUrl(createClaimRequest.getProofUrl())
                .status(Claim.STATUSES.SUBMITTED)
                .category(createClaimRequest.getCategory())
                .approvalMode(createClaimRequest.getApprovalMode())
                .comment(createClaimRequest.getComment())
                .budget(budgetById)
                .employee(employeeById)
                .build();

        Claim savedClaim = claimRepository.save(newClaim);

        if (employeeById != null) {
            employeeById.getClaims().add(savedClaim);
        }

        if (budgetById != null) {
            budgetById.getClaims().add(savedClaim);
        }

        return claimMapper.toClaimResponse(savedClaim);
    }

    public ClaimResponse updateStatus(Long id, Claim.STATUSES status) {
        Claim claimById = claimRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim with ID: " + id + " does not exists!"));

        if (claimById.getStatus() == Claim.STATUSES.REJECTED) {
            throw new RuntimeException("Cannot update status of a rejected claim!");
        }

        if (claimById.getStatus() == Claim.STATUSES.PAID) {
            throw new RuntimeException("Cannot update status of an already paid claim!");
        }

        if (status != Claim.STATUSES.REJECTED && status.ordinal() != claimById.getStatus().ordinal() + 1) {
            throw new IllegalStateException("Cannot skip status!");
        }

        claimById.setStatus(status);

        return claimMapper.toClaimResponse(claimRepository.save(claimById));
    }

    public void deleteClaimById(Long id) {
        claimRepository.deleteById(id);
    }
}
