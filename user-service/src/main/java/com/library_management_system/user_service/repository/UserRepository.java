package com.library_management_system.user_service.repository;

import com.library_management_system.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByNameContainingIgnoreCase(String name);
    Optional<User> findByEmail(String email);
}
