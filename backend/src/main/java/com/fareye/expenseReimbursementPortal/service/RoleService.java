package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.mapper.RoleMapper;
import com.fareye.expenseReimbursementPortal.model.dto.RoleResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Role;
import com.fareye.expenseReimbursementPortal.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleResponse> getAllRoles() {
        return roleMapper.toListOfRoleResponses(roleRepository.findAll());
    }

    public RoleResponse getRoleById(Long id) {
        return roleMapper.toRoleResponse(roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role with ID: " + id + " does not exists!")));
    }

    public RoleResponse createRole(String role) {
        Role newRole = Role.builder().role(role).build();

        return roleMapper.toRoleResponse(roleRepository.save(newRole));
    }

    public RoleResponse updateRole(Long id, String role) {
        Role roleById = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role with ID: " + id + " does not exists!"));

        roleById.setRole(role);

        return roleMapper.toRoleResponse(roleRepository.save(roleById));
    }

    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
