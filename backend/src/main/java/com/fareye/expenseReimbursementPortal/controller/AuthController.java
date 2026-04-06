package com.fareye.expenseReimbursementPortal.controller;

import com.fareye.expenseReimbursementPortal.model.dto.CreateUserRequest;
import com.fareye.expenseReimbursementPortal.model.dto.LoginRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UserResponse;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import com.fareye.expenseReimbursementPortal.service.AuthService;
import com.fareye.expenseReimbursementPortal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest, request));
    }

//    @PostMapping("/signup")
//    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(createUserRequest));
//    }

    @GetMapping("/current")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
    }
}
