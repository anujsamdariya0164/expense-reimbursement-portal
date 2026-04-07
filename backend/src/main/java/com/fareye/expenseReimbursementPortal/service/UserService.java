package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.exception.EmailAlreadyExistsException;
import com.fareye.expenseReimbursementPortal.exception.ResourceNotFoundException;
import com.fareye.expenseReimbursementPortal.mapper.UserMapper;
import com.fareye.expenseReimbursementPortal.model.dto.CreateUserRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UserResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import com.fareye.expenseReimbursementPortal.model.entity.Role;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import com.fareye.expenseReimbursementPortal.repository.DepartmentRepository;
import com.fareye.expenseReimbursementPortal.repository.RoleRepository;
import com.fareye.expenseReimbursementPortal.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final DepartmentRepository departmentRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserResponse> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return userMapper.toListOfUserResponse(allUsers);
    }

    public UserResponse getUserById(Long id) {
        User userById = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + id + " not found!"));

        return userMapper.toUserResponse(userById);
    }

    public UserResponse getUserByEmail(String email) {
        return userMapper.toUserResponse(userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with the email ID: " + email + " not found!")));
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) throws AccessDeniedException {
        try {
            if (createUserRequest.getRoleId() == 1) {
                throw new AccessDeniedException("Cannot create ADMIN!");
            }

            Role roleById = roleRepository.findById(createUserRequest.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role with ID :" + createUserRequest.getRoleId() + " does not exists!"));

            Department departmentById = departmentRepository.findById(createUserRequest.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department with ID: " + createUserRequest.getDepartmentId() + " does not exists!"));

            User managerById = (departmentById.getManager() != null) ?
                    userRepository.findById(departmentById.getManager().getId()).orElseThrow(() -> new ResourceNotFoundException("Manager with ID: " + departmentById.getManager().getId() + " does not exists!")) :
                    null;

            if (createUserRequest.getRoleId() == 2) {
                if (departmentById.getManager() != null) {
                    throw new ResourceNotFoundException("Department with ID: " + createUserRequest.getDepartmentId() + " already has a manager assigned!");
                }
            }

            String encodedPassword = passwordEncoder.encode("password123");

            User newUser = User.builder()
                    .name(createUserRequest.getName())
                    .email(createUserRequest.getEmail())
                    .password(encodedPassword)
                    .role(roleById)
                    .department(departmentById)
                    .manager(managerById)
                    .build();

            User savedUser = userRepository.save(newUser);

            if (createUserRequest.getRoleId() == 2) {
                departmentById.setManager(savedUser);
                departmentRepository.save(departmentById);
            }

            return userMapper.toUserResponse(savedUser);
        } catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException("Email already exists!");
        } catch (AccessDeniedException e) {
            throw new AccessDeniedException(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyExistsException("A user with this email already exists!");
        }
    }

//    TODO: Implement update user function below
//    public User updateUser() {}

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " does not exists!"));
    }
}
