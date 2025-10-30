package com.library_management_system.user_service.mapper;

import com.library_management_system.user_service.dto.UserRequestDTO;
import com.library_management_system.user_service.dto.UserResponseDTO;
import com.library_management_system.user_service.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public UserResponseDTO userToResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setStudent(user.isStudent());
        userResponseDTO.setDateOfBirth(user.getDateOfBirth());

        return userResponseDTO;
    }

    public User userRequestToUser(UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setEmail(userRequestDTO.getEmail());
        user.setStudent(userRequestDTO.isStudent());
        user.setDateOfBirth(userRequestDTO.getDateOfBirth());

        return user;
    }

    public User userUpdateRequestToExistingUser(User user, UserRequestDTO userRequestDTO) {
        user.setBalance(userRequestDTO.getBalance());
        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setEmail(userRequestDTO.getEmail());
        user.setStudent(userRequestDTO.isStudent());

        return user;
    }
}
