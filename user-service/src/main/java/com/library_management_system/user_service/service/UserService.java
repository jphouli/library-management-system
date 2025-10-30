package com.library_management_system.user_service.service;

import com.library_management_system.user_service.dto.UserRequestDTO;
import com.library_management_system.user_service.dto.UserResponseDTO;
import com.library_management_system.user_service.exception.UserNotFoundException;
import com.library_management_system.user_service.mapper.UserMapper;
import com.library_management_system.user_service.model.User;
import com.library_management_system.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.userRequestToUser(userRequestDTO);
        return userMapper.userToResponseDTO(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream().map(userMapper::userToResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with ID: " + id));
        return userMapper.userToResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getUsersByNameContainingIgnoreCase(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
                .stream().map(userMapper::userToResponseDTO).toList();
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO user) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with ID: " + id));
        User updatedUser = userMapper.userUpdateRequestToExistingUser(existingUser, user);

        return userMapper.userToResponseDTO(userRepository.save(updatedUser));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
