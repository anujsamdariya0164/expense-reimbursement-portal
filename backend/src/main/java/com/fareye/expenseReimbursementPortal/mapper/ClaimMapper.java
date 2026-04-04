package com.fareye.expenseReimbursementPortal.mapper;

import com.fareye.expenseReimbursementPortal.model.dto.ClaimResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClaimMapper {
    public ClaimResponse toClaimResponse(Claim claim) {
        return ClaimResponse.builder()
                .id(claim.getId())
                .amount(claim.getAmount())
                .category(claim.getCategory())
                .comment(claim.getComment())
                .proofUrl(claim.getProofUrl())
                .status(claim.getStatus())
                .approvalMode(claim.getApprovalMode())
                .budgetId(claim.getBudget() == null ? null : claim.getBudget().getId())
                .employeeId(claim.getEmployee() == null ? null : claim.getEmployee().getId() )
                .employeeName(claim.getEmployee() == null ? null : claim.getEmployee().getName() )
                .employeeEmail(claim.getEmployee() == null ? null : claim.getEmployee().getEmail())
                .departmentId(claim.getAssignedDepartment() == null ? null : claim.getAssignedDepartment().getId())
                .departmentName(claim.getAssignedDepartment() == null ? null : claim.getAssignedDepartment().getName())
                .build();
    }

    public List<ClaimResponse> toListOfClaimResponse(List<Claim> claims) {
        return claims.stream().map(claim ->
                toClaimResponse(claim)
        ).toList();
    }
}
