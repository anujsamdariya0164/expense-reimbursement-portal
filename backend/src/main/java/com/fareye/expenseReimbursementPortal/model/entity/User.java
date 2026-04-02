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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "manager")
    private Department assignedDepartment;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<User> employees;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Claim> claims;

    @OneToMany(mappedBy = "actor")
    private List<AuditLog> auditLog;
}
