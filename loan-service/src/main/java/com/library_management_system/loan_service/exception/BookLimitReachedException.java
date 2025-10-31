package com.library_management_system.loan_service.exception;

public class BookLimitReachedException extends RuntimeException {
    public BookLimitReachedException(String message) {
        super(message);
    }
}
