package com.fareye.expenseReimbursementPortal.mapper;

import com.fareye.expenseReimbursementPortal.model.dto.AuditLogResponse;
import com.fareye.expenseReimbursementPortal.model.entity.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuditLogMapper {
    public AuditLogResponse toAuditLogResponse(AuditLog auditLog) {
        return AuditLogResponse.builder()
                .id(auditLog.getId())
                .action(auditLog.getAction())
                .timestamp(auditLog.getTimestamp())
                .actorId(auditLog.getActor() == null ? null : auditLog.getActor().getId())
                .actorName(auditLog.getActor() == null ? null : auditLog.getActor().getName())
                .actorEmail(auditLog.getActor() == null ? null : auditLog.getActor().getEmail())
                .role(auditLog.getActor() == null ? null : auditLog.getActor().getRole().getRole())
                .claimId(auditLog.getClaim() == null ? null : auditLog.getClaim().getId())
                .build();
    }

    public Page<AuditLogResponse> toListOfAuditLogResponse(Page<AuditLog> auditLogs) {
        return auditLogs.map(this::toAuditLogResponse);
    }
}
