package com.fareye.expenseReimbursementPortal.repository;

import com.fareye.expenseReimbursementPortal.model.entity.Claim;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findClaimByEmployee(User employee);

    List<Claim> findClaimByAssignedDepartment(Department department);
}
