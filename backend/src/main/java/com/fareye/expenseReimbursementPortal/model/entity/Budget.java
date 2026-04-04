package com.fareye.expenseReimbursementPortal.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maximum_limit")
    private Long limit;

    @Column(name = "used_amount")
    private Long usedAmount;

    @OneToMany(mappedBy = "budget")
    private List<Claim> claims;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
