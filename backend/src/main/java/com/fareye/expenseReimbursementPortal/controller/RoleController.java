package com.fareye.expenseReimbursementPortal.controller;

import com.fareye.expenseReimbursementPortal.model.dto.RoleRequest;
import com.fareye.expenseReimbursementPortal.model.dto.RoleResponse;
import com.fareye.expenseReimbursementPortal.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoleById(Long.parseLong(id)));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest createRoleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(createRoleRequest.getRole()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRoleById(@PathVariable String id, @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(Long.parseLong(id), roleRequest.getRole()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable String id) {
        roleService.deleteRoleById(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
