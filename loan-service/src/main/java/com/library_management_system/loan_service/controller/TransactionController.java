package com.library_management_system.loan_service.controller;

import com.library_management_system.loan_service.dto.ReturnBookRequestDTO;
import com.library_management_system.loan_service.dto.IssueBookRequestDTO;
import com.library_management_system.loan_service.dto.TransactionResponseDTO;
import com.library_management_system.loan_service.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/issue-book")
    public ResponseEntity<TransactionResponseDTO> issueBook(@RequestBody IssueBookRequestDTO transactionRequestDTO) {
        return ResponseEntity.ok(transactionService.issueBook(transactionRequestDTO.getBookId(), transactionRequestDTO.getUserId()));
    }

    @PostMapping("/return-book")
    public ResponseEntity<Double> returnBook(@RequestBody ReturnBookRequestDTO returnBookRequestDTO) {
        return ResponseEntity.ok(transactionService.returnBook(returnBookRequestDTO.getBookId()));
    }
}
