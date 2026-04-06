package com.fareye.expenseReimbursementPortal.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EmailAlreadyExistsException extends DataIntegrityViolationException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
