package com.fareye.expenseReimbursementPortal.controller;

import com.fareye.expenseReimbursementPortal.exception.EmailAlreadyExistsException;
import com.fareye.expenseReimbursementPortal.exception.ResourceNotFoundException;
import com.fareye.expenseReimbursementPortal.model.dto.CreateUserRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UserResponse;
import com.fareye.expenseReimbursementPortal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(Long.parseLong(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws ResourceNotFoundException, EmailAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(createUserRequest));
    }

//    @PutMapping("/{id}")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userService.deleteUser(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
