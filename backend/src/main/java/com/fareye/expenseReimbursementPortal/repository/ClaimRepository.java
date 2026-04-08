package com.fareye.expenseReimbursementPortal.repository;

import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Page<Claim> findClaimByEmployee(User employee, Pageable pageable);

    List<Claim> findClaimByAssignedDepartment(Department department);
}
