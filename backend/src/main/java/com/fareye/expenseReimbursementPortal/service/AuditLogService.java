package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.mapper.AuditLogMapper;
import com.fareye.expenseReimbursementPortal.model.dto.AuditLogResponse;
import com.fareye.expenseReimbursementPortal.model.dto.CreateAuditLogRequest;
import com.fareye.expenseReimbursementPortal.model.entity.AuditLog;
import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import com.fareye.expenseReimbursementPortal.repository.AuditLogRepository;
import com.fareye.expenseReimbursementPortal.repository.ClaimRepository;
import com.fareye.expenseReimbursementPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    private final AuditLogMapper auditLogMapper;

    private final UserRepository userRepository;

    private final ClaimRepository claimRepository;

    public AuditLogService(AuditLogRepository auditLogRepository, AuditLogMapper auditLogMapper, UserRepository userRepository, ClaimRepository claimRepository) {
        this.auditLogRepository = auditLogRepository;

        this.auditLogMapper = auditLogMapper;

        this.userRepository = userRepository;

        this.claimRepository = claimRepository;
    }

    public List<AuditLogResponse> getAllAuditLogs() {
        return auditLogMapper.toListOfAuditLogResponse(auditLogRepository.findAll());
    }

    public AuditLogResponse getAuditLogById(Long id) {
        return auditLogMapper.toAuditLogResponse(auditLogRepository.findById(id).orElseThrow(() -> new RuntimeException("Audit log with ID: " + id + " does not exists!")));
    }

    public AuditLogResponse createAuditLog(CreateAuditLogRequest createAuditLogRequest) {
        User actorById = userRepository.findById(createAuditLogRequest.getActorId()).orElseThrow(() -> new RuntimeException("Actor with ID: " + createAuditLogRequest.getActorId() + " does not exists!"));

        Claim claimById = claimRepository.findById(createAuditLogRequest.getClaimId()).orElseThrow(() -> new RuntimeException("Claim with ID: " + createAuditLogRequest.getClaimId() + " does not exists!"));

        AuditLog newAuditLog = AuditLog.builder()
                .action(createAuditLogRequest.getAction())
                .timestamp(LocalDateTime.now())
                .actor(actorById)
                .claim(claimById)
                .build();

        actorById.getAuditLog().add(newAuditLog);
        userRepository.save(actorById);

        claimById.getAuditLogs().add(newAuditLog);
        claimRepository.save(claimById);

        return auditLogMapper.toAuditLogResponse(auditLogRepository.save(newAuditLog));
    }

    public void deleteAuditLogById(Long id) {
        auditLogRepository.deleteById(id);
    }
}
