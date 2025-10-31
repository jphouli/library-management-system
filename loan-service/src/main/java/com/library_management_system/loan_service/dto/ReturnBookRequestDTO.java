package com.library_management_system.loan_service.dto;

import java.util.UUID;

public class ReturnBookRequestDTO {
    private UUID bookId;

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }
}
