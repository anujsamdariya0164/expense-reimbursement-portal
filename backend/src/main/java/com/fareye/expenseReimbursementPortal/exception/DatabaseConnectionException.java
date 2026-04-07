package com.fareye.expenseReimbursementPortal.exception;

import org.springframework.dao.DataAccessException;

public class DatabaseConnectionException extends DataAccessException {
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
