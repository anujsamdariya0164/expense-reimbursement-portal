package com.fareye.expenseReimbursementPortal.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "claims")
public class Claim {
    public enum STATUSES {
        REJECTED, DRAFT, SUBMITTED, APPROVED, PAID
    }

    public enum CATEGORIES {
        TRAVEL, TRANSPORTATION, MEALS, ENTERTAINMENT, SUPPLIES, INTERNET, TRAINING
    }

    public enum APPROVAL_MODE {
        AUTO, MANAGER, MANAGER_AND_ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "proof_url")
    private String proofUrl;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private STATUSES status;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CATEGORIES category;

    @Enumerated(EnumType.STRING)
    private APPROVAL_MODE approvalMode;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @OneToMany(mappedBy = "claim")
    private List<AuditLog> auditLogs;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department assignedDepartment;
}
