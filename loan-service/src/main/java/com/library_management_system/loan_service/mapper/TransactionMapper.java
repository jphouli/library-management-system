package com.library_management_system.loan_service.mapper;

import com.library_management_system.loan_service.dto.TransactionResponseDTO;
import com.library_management_system.loan_service.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponseDTO transactionToResponse(Transaction transaction) {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();

        transactionResponseDTO.setId(transaction.getId());
        transactionResponseDTO.setBookId(transaction.getBookId());
        transactionResponseDTO.setUserId(transaction.getUserId());
        transactionResponseDTO.setIssueDate(transaction.getIssueDate());
        transactionResponseDTO.setStatus(transaction.isStatus());

        return transactionResponseDTO;
    }
}
