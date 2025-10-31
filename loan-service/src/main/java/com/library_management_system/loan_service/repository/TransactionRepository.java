package com.library_management_system.loan_service.repository;

import com.library_management_system.loan_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByBookIdAndStatusTrue(UUID bookId);
    List<Transaction> findAllByUserIdAndStatusTrue(UUID userId);
}
