package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.exception.EmailPasswordNotProvided;
import com.fareye.expenseReimbursementPortal.exception.SessionAlreadyInvalidated;
import com.fareye.expenseReimbursementPortal.mapper.UserMapper;
import com.fareye.expenseReimbursementPortal.model.dto.LoginRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UserResponse;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final UserMapper userMapper;

    public AuthService(AuthenticationManager authenticationManager, UserService userService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new EmailPasswordNotProvided();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        User user = (User) authentication.getPrincipal();

        return userMapper.toUserResponse(user);
    }

    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated!");
        }

        String email = authentication.getName();

        return userService.getUserByEmail(email);
    }

    public void logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        } catch (IllegalStateException e) {
            throw new SessionAlreadyInvalidated();
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
