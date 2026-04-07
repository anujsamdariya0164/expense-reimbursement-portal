package com.fareye.expenseReimbursementPortal.exception;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class CategoryNotProvidedException extends HttpMessageNotReadableException {
    public CategoryNotProvidedException(String message, HttpInputMessage httpInputMessage) {
        super(message, httpInputMessage);
    }
}
