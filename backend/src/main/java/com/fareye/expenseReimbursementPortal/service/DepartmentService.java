package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.mapper.DepartmentMapper;
import com.fareye.expenseReimbursementPortal.model.dto.DepartmentRequest;
import com.fareye.expenseReimbursementPortal.model.dto.DepartmentResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import com.fareye.expenseReimbursementPortal.repository.DepartmentRepository;
import com.fareye.expenseReimbursementPortal.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    private final UserRepository userRepository;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;

        this.departmentMapper = departmentMapper;

        this.userRepository = userRepository;
    }

    public List<DepartmentResponse> getAllDepartments() {
        return departmentMapper.toListOfDepartmentResponses(departmentRepository.findAll());

    }

    public DepartmentResponse getDepartmentById(Long id) {
        return departmentMapper.toDepartmentResponse(departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department with ID: " + id + " does not exists!")));
    }

    public DepartmentResponse createDepartment(DepartmentRequest createDepartmentRequest) {
        User managerById = null;
        Long managerId = createDepartmentRequest.getManagerId();
        if (managerId != null) {
            managerById = userRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager with ID: " + managerId + " does not exists!"));
        }

        Department newDepartment = Department.builder()
                .name(createDepartmentRequest.getName())
                .manager(managerById)
                .build();

        if (managerById != null) {
            managerById.setAssignedDepartment(newDepartment);
            userRepository.save(managerById);
        }

        Department savedDepartment = departmentRepository.save(newDepartment);

        return departmentMapper.toDepartmentResponse(savedDepartment);
    }

    public DepartmentResponse updateDepartmentById(Long id, DepartmentRequest departmentRequest) {
        Department departmentById = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department with ID: " + id + " does not exists!"));

        if (departmentRequest.getName() != null && !departmentRequest.getName().equals(departmentById.getName())) {
            departmentById.setName(departmentRequest.getName());
        }

        if (departmentRequest.getManagerId() != null) {
            if (departmentById.getManager() == null || departmentById.getManager().getId() != departmentRequest.getManagerId()) {
                User managerById = userRepository.findById(departmentRequest.getManagerId()).orElseThrow(() -> new RuntimeException("Manager with ID: " + departmentById.getManager().getId() + " does not exists!"));

                if (managerById.getAssignedDepartment() != null) {
                    throw new RuntimeException("Manager with ID: " + managerById.getId() + " already has a department assigned!");
                }

                departmentById.setManager(managerById);

                managerById.setAssignedDepartment(departmentById);
                userRepository.save(managerById);
            }
        }

        Department updatedDepartment = departmentRepository.save(departmentById);

        return departmentMapper.toDepartmentResponse(updatedDepartment);
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
