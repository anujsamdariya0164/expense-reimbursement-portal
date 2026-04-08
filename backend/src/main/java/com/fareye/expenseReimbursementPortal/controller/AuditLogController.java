package com.fareye.expenseReimbursementPortal.controller;

import com.fareye.expenseReimbursementPortal.model.dto.AuditLogResponse;
import com.fareye.expenseReimbursementPortal.model.dto.CreateAuditLogRequest;
import com.fareye.expenseReimbursementPortal.model.dto.PaginationRequest;
import com.fareye.expenseReimbursementPortal.service.AuditLogService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auditlogs")
public class AuditLogController {
    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<AuditLogResponse>> getAllAuditLogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(auditLogService.getAllAuditLogs(page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuditLogResponse> getAuditLogById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(auditLogService.getAuditLogById(Long.parseLong(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuditLogResponse> createAuditLog(@RequestBody CreateAuditLogRequest createAuditLogRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditLogService.createAuditLog(createAuditLogRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAuditLogById(@PathVariable String id) {
        auditLogService.deleteAuditLogById(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
