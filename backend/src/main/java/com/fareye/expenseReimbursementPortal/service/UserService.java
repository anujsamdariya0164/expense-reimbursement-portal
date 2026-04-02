package com.fareye.expenseReimbursementPortal.service;

import com.fareye.expenseReimbursementPortal.mapper.UserMapper;
import com.fareye.expenseReimbursementPortal.model.dto.CreateUserRequest;
import com.fareye.expenseReimbursementPortal.model.dto.UserResponse;
import com.fareye.expenseReimbursementPortal.model.entity.Department;
import com.fareye.expenseReimbursementPortal.model.entity.Role;
import com.fareye.expenseReimbursementPortal.model.entity.User;
import com.fareye.expenseReimbursementPortal.repository.DepartmentRepository;
import com.fareye.expenseReimbursementPortal.repository.RoleRepository;
import com.fareye.expenseReimbursementPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final DepartmentRepository departmentRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponse> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return userMapper.toListOfUserResponse(allUsers);
    }

    public UserResponse getUserById(Long id) {
        User userById = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found!"));

        return userMapper.toUserResponse(userById);
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        Role roleById = roleRepository.findById(createUserRequest.getRoleId()).orElseThrow(() -> new RuntimeException("Role with ID :" + createUserRequest.getRoleId() + " does not exists!"));

        Department departmentById = departmentRepository.findById(createUserRequest.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department with ID: " + createUserRequest.getDepartmentId() + " does not exists!"));

        User managerById = (createUserRequest.getManagerId() != null) ?
                userRepository.findById(createUserRequest.getManagerId()).orElseThrow(() -> new RuntimeException("Manager with ID: " + createUserRequest.getManagerId() + " does not exists!")) :
                null;

        User newUser = User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .role(roleById)
                .department(departmentById)
                .manager(managerById)
                .build();

        if (createUserRequest.getRoleId() == 2) {
//        TODO: Assign manager here to departmentById
        }

        User savedUser = userRepository.save(newUser);

        return userMapper.toUserResponse(savedUser);
    }

//    TODO: Implement update user function below
//    public User updateUser() {}

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
