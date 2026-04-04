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
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maximum_limit")
    private Long limit;

    @Column(name = "amount")
    private Long amount;

    @OneToMany(mappedBy = "budget")
    private List<Claim> claims;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department departmentAssigned;
}
