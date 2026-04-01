package com.fareye.expenseReimbursementPortal.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "claims")
public class Claim {
    public enum STATUSES {
        DRAFT, SUBMITTED, NEEDS_INFORMATION, APPROVED, REJECTED, PAID
    }

    public enum CATEGORIES {
        TRAVEL, TRANSPORTATION, MEALS, ENTERTAINMENT, SUPPLIES, INTERNET, TRAINING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;
}
