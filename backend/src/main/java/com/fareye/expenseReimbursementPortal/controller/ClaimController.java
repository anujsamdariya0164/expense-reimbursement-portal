package com.fareye.expenseReimbursementPortal.controller;

import com.fareye.expenseReimbursementPortal.model.dto.CreateClaimRequest;
import com.fareye.expenseReimbursementPortal.model.dto.ClaimResponse;
import com.fareye.expenseReimbursementPortal.model.dto.UpdateClaimRequest;
import com.fareye.expenseReimbursementPortal.service.ClaimService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public ResponseEntity<List<ClaimResponse>> getAllClaims() {
        return ResponseEntity.status(HttpStatus.OK).body(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimResponse> getClaimById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(claimService.getClaimById(Long.parseLong(id)));
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<ClaimResponse>> getClaimsByEmployee(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(claimService.getClaimsMadeByEmployee(Long.parseLong(id)));
    }

    @GetMapping("/department/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<List<ClaimResponse>> getClaimsByDepartment(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(claimService.getClaimsByDepartment(Long.parseLong(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ClaimResponse> createClaim(@RequestBody CreateClaimRequest createClaimRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claimService.createClaim(createClaimRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<ClaimResponse> updateClaimById(@PathVariable String id, @RequestBody UpdateClaimRequest updateClaimRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(claimService.updateStatus(Long.parseLong(id), updateClaimRequest.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClaimById(@PathVariable String id) {
        claimService.deleteClaimById(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
