package com.library_management_system.loan_service.service;

import com.library_management_system.loan_service.dto.TransactionResponseDTO;
import com.library_management_system.loan_service.exception.BookLimitReachedException;
import com.library_management_system.loan_service.exception.BookNotAvailableException;
import com.library_management_system.loan_service.exception.TransactionNotFoundException;
import com.library_management_system.loan_service.mapper.TransactionMapper;
import com.library_management_system.loan_service.model.Transaction;
import com.library_management_system.loan_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final int MAX_BOOK_LIMIT = 3;
    private final int MAX_LOAN_DAYS = 14;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public TransactionResponseDTO createTransaction(Transaction transaction) {
        return transactionMapper.transactionToResponse(transactionRepository.save(transaction));
    }

    public boolean isBookAvailable(UUID bookId) {
        Optional<Transaction> transaction = transactionRepository.findByBookIdAndStatusTrue(bookId);

        return transaction.isEmpty();
    }

    public int getBorrowCount(UUID userId) {
        return transactionRepository.findAllByUserIdAndStatusTrue(userId).size();
    }

    public LocalDate getDueDate(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found for ID: " + id));

        return transaction.getIssueDate().plusDays(MAX_LOAN_DAYS);
    }

    public TransactionResponseDTO issueBook(UUID bookId, UUID userId) {
        Transaction transaction = new Transaction();

        transaction.setBookId(bookId);
        transaction.setUserId(userId);
        transaction.setStatus(true);
        transaction.setIssueDate(LocalDate.now());

        int userBookCount = getBorrowCount(userId);

        if (userBookCount >= MAX_BOOK_LIMIT) {
            throw new BookLimitReachedException("User has reached the limit of books checked out.");
        }

        boolean isBookAvailable = isBookAvailable(bookId);

        if (!isBookAvailable) {
            throw new BookNotAvailableException("Book has already been checked out.");
        }

        return createTransaction(transaction);
    }

    public double returnBook(UUID bookId) {
        double fine = 0;

        Transaction transaction = transactionRepository.findByBookIdAndStatusTrue(bookId)
                .orElseThrow(() -> new TransactionNotFoundException("Ongoing transaction not found"));

        if (LocalDate.now().isAfter(getDueDate(bookId))) {
            fine += 15;
        }

        transaction.setStatus(false);
        transactionRepository.save(transaction);

        return fine;
    }
}
