package com.library_management_system.loan_service.dto;

import java.util.UUID;

public class IssueBookRequestDTO {
    private UUID bookId;
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }
}
