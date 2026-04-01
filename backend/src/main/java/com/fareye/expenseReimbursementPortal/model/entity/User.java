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
@Table(name = "users")
public class User {
    public enum ROLES {
        ADMIN, MANAGER, EMPLOYEE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    private ROLES role;

    @Column(name = "password")
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
