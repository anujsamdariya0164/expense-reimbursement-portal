package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.model.dto.LoginRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public UserResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            System.out.println(userDetails.getUsername());

            return userService.getUserByEmail(userDetails.getUsername());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid password provided!");
        }
    }

    public UserResponse getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String email = authentication.getName();

            if (email.equals("anonymousUser")) {
                throw new AuthenticationCredentialsNotFoundException("User not authenticated!");
            }

            return userService.getUserByEmail(email);
        } catch (AuthenticationCredentialsNotFoundException e) {
            throw new AuthenticationCredentialsNotFoundException(e.getMessage());
        }
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();
    }
}
