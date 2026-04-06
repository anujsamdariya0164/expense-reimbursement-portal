package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.mapper.ClaimMapper;
import com.fareye.expenseReimbursementPortal.model.dto.CreateAuditLogRequest;
import com.fareye.expenseReimbursementPortal.model.dto.CreateClaimRequest;
import com.fareye.expenseReimbursementPortal.model.dto.ClaimResponse;
import com.fareye.expenseReimbursementPortal.model.dto.UpdateBudgetRequest;
import com.fareye.expenseReimbursementPortal.model.entity.Budget;
import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import com.fareye.expenseReimbursementPortal.repository.BudgetRepository;
import com.fareye.expenseReimbursementPortal.repository.ClaimRepository;
import com.fareye.expenseReimbursementPortal.repository.DepartmentRepository;
import com.fareye.expenseReimbursementPortal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    private final ClaimMapper claimMapper;

    private final BudgetRepository budgetRepository;

    private final UserRepository userRepository;

    private final BudgetService budgetService;

    private final DepartmentRepository departmentRepository;

    private final AuditLogService auditLogService;

    public ClaimService(ClaimRepository claimRepository, ClaimMapper claimMapper, BudgetRepository budgetRepository, UserRepository userRepository, BudgetService budgetService, DepartmentRepository departmentRepository, AuditLogService auditLogService) {
        this.claimRepository = claimRepository;

        this.claimMapper = claimMapper;

        this.budgetRepository = budgetRepository;

        this.userRepository = userRepository;

        this.budgetService = budgetService;

        this.departmentRepository = departmentRepository;

        this.auditLogService = auditLogService;
    }

    public List<ClaimResponse> getAllClaims() {
        return claimMapper.toListOfClaimResponse(claimRepository.findAll());
    }

    public ClaimResponse getClaimById(Long id) {
        return claimMapper.toClaimResponse(claimRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim with ID: " + id + " does not exists!")));
    }

    public List<ClaimResponse> getClaimsMadeByEmployee(Long employeeId) {
        User employeeById = userRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee with ID: " + employeeId + " does not exists!"));

        return claimMapper.toListOfClaimResponse(claimRepository.findClaimByEmployee(employeeById));
    }

    public List<ClaimResponse> getClaimsByDepartment(Long departmentId) {
        Department departmentById = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department with ID: " + departmentId + " does not exists!"));

        return claimMapper.toListOfClaimResponse(claimRepository.findClaimByAssignedDepartment(departmentById));
    }

    public ClaimResponse createClaim(CreateClaimRequest createClaimRequest) {
        System.out.println("Create claim service function!");
        User employeeById = null;
        if (createClaimRequest.getEmployeeId() != null) {
            employeeById = userRepository.findById(createClaimRequest.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee with ID: " + createClaimRequest.getEmployeeId() + " does not exists!"));
        }

        Department departmentById = null;
        Long departmentId = employeeById.getDepartment().getId();
        if (departmentId != null) {
            departmentById = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department with ID: " + departmentId + " does not exists!"));
        }

        Budget budgetById = null;
        Long budgetId = employeeById.getDepartment().getBudget().getId();
        if (budgetId != null) {
            budgetById = budgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException("Budget with ID: " + budgetId + " does not exists!"));
        }

        Claim newClaim = Claim.builder()
                .amount(createClaimRequest.getAmount())
                .proofUrl(createClaimRequest.getProofUrl())
                .status(Claim.STATUSES.SUBMITTED)
                .category(createClaimRequest.getCategory())
                .approvalMode(Claim.APPROVAL_MODE.AUTO)
                .comment(createClaimRequest.getComment())
                .budget(budgetById)
                .employee(employeeById)
                .assignedDepartment(departmentById)
                .stage(0)
                .build();

        if (
                (newClaim.getCategory() == Claim.CATEGORIES.MEALS && newClaim.getAmount() > 50 && (newClaim.getComment() == null || newClaim.getComment().isEmpty())) ||
                (newClaim.getAmount() + newClaim.getBudget().getAmount() > newClaim.getBudget().getLimit())
        ) {
            newClaim.setStatus(Claim.STATUSES.REJECTED);
            newClaim.setApprovalMode(Claim.APPROVAL_MODE.AUTO);
        } else if (newClaim.getAmount() <= 100) {
            newClaim.setApprovalMode(Claim.APPROVAL_MODE.AUTO);
            newClaim.setStatus(Claim.STATUSES.APPROVED);
        } else if (newClaim.getAmount() <= 1000) {
            newClaim.setApprovalMode(Claim.APPROVAL_MODE.MANAGER);
        } else {
            newClaim.setApprovalMode(Claim.APPROVAL_MODE.MANAGER_AND_ADMIN);
        }

        Claim savedClaim = claimRepository.save(newClaim);

        if (employeeById != null) {
            if (employeeById.getClaims() == null) employeeById.setClaims(new ArrayList<>());
            employeeById.getClaims().add(savedClaim);
            userRepository.save(employeeById);
        }

        if (budgetById != null) {
            if (budgetById.getClaims() == null) budgetById.setClaims(new ArrayList<>());
            budgetById.getClaims().add(savedClaim);
            budgetRepository.save(budgetById);
        }

        if (departmentById != null) {
            if (departmentById.getClaims() == null) departmentById.setClaims(new ArrayList<>());
            departmentById.getClaims().add(savedClaim);
            departmentRepository.save(departmentById);
        }

        CreateAuditLogRequest createAuditLogRequest = CreateAuditLogRequest.builder()
                .action(savedClaim.getStatus())
                .actorId(savedClaim.getEmployee().getId())
                .claimId(savedClaim.getId())
                .build();

        auditLogService.createAuditLog(createAuditLogRequest);

        return claimMapper.toClaimResponse(savedClaim);
    }

    public ClaimResponse updateStatus(Long id, Claim.STATUSES status) {
        System.out.println("Starting updateStatus claim service function");
        Claim claimById = claimRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim with ID: " + id + " does not exists!"));

        if (claimById.getAssignedDepartment().getId() != claimById.getEmployee().getDepartment().getId()) {
            throw new RuntimeException("The claim is of another budget!");
        }


        if (claimById.getStatus() == Claim.STATUSES.REJECTED) {
            throw new RuntimeException("Cannot update status of a rejected claim!");
        }

        if (claimById.getStatus() == Claim.STATUSES.PAID) {
            throw new RuntimeException("Cannot update status of an already paid claim!");
        }

        if (status != Claim.STATUSES.REJECTED && status.ordinal() != claimById.getStatus().ordinal() + 1) {
            throw new IllegalStateException("Cannot skip status!");
        }

        if ((claimById.getAmount() + claimById.getBudget().getAmount() > claimById.getBudget().getLimit()) && status == Claim.STATUSES.APPROVED) {
            throw new RuntimeException("Budget Overflow!");
        }

        if (status == Claim.STATUSES.PAID) {
            UpdateBudgetRequest updateBudgetRequest = UpdateBudgetRequest.builder()
                    .amount(claimById.getAmount())
                    .build();
            budgetService.updateBudgetById(claimById.getBudget().getId(), updateBudgetRequest);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            throw new RuntimeException("User is not authenticated!");
        }

        User user = userRepository.findUserByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("User with email: " + authentication.getName() + " does not exists!"));

        if (user.getRole().getRole().equals("EMPLOYEE")) {
            throw new RuntimeException("Employee cannot update any claim!");
        }

        if (user.getRole().getRole().equals("MANAGER") && status.equals(Claim.STATUSES.PAID)) {
            throw new RuntimeException("Only admin can perform final disbursement!");
        }

        if (user.getRole().getRole().equals("ADMIN") || (user.getRole().getRole().equals("MANAGER") && claimById.getApprovalMode().equals(Claim.APPROVAL_MODE.MANAGER))) {
            claimById.setStatus(status);
        }

        if (user.getRole().getRole().equals("MANAGER") && claimById.getApprovalMode().equals(Claim.APPROVAL_MODE.MANAGER_AND_ADMIN) && claimById.getStage() == 1) {
            throw new RuntimeException("Admin approval required!");
        } else if (user.getRole().getRole().equals("MANAGER") && claimById.getApprovalMode().equals(Claim.APPROVAL_MODE.MANAGER_AND_ADMIN) && claimById.getStage() == 0) {
            claimById.setStage(1);
        }

        Claim updatedClaim = claimRepository.save(claimById);

        CreateAuditLogRequest createAuditLogRequest = CreateAuditLogRequest.builder()
                .action(updatedClaim.getStatus())
                .actorId(user.getId()) // take out the user from current session and fill
                .claimId(updatedClaim.getId())
                .build();

        auditLogService.createAuditLog(createAuditLogRequest);

        return claimMapper.toClaimResponse(updatedClaim);
    }

    public void deleteClaimById(Long id) {
        claimRepository.deleteById(id);
    }
}
