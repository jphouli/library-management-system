package com.library_management_system.user_service.controller;

import com.library_management_system.user_service.dto.UserRequestDTO;
import com.library_management_system.user_service.dto.UserResponseDTO;
import com.library_management_system.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/search/")
    public ResponseEntity<List<UserResponseDTO>> getUsersByNameContainingIgnoreCase(
            @RequestParam(name="case") String name) {
        return ResponseEntity.ok(userService.getUsersByNameContainingIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.addUser(userRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
