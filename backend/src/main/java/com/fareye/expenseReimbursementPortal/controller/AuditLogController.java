package com.fareye.expenseReimbursementPortal.controller;

import com.fareye.expenseReimbursementPortal.model.dto.AuditLogResponse;
import com.fareye.expenseReimbursementPortal.model.dto.CreateAuditLogRequest;
import com.fareye.expenseReimbursementPortal.service.AuditLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditlogs")
public class AuditLogController {
    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public ResponseEntity<List<AuditLogResponse>> getAllAuditLogs() {
        return ResponseEntity.status(HttpStatus.OK).body(auditLogService.getAllAuditLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogResponse> getAuditLogById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(auditLogService.getAuditLogById(Long.parseLong(id)));
    }

    @PostMapping
    public ResponseEntity<AuditLogResponse> createAuditLog(@RequestBody CreateAuditLogRequest createAuditLogRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditLogService.createAuditLog(createAuditLogRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuditLogById(@PathVariable String id) {
        auditLogService.deleteAuditLogById(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
