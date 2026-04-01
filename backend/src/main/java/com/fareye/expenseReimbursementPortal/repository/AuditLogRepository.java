package com.fareye.expenseReimbursementPortal.repository;

import com.fareye.expenseReimbursementPortal.model.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
