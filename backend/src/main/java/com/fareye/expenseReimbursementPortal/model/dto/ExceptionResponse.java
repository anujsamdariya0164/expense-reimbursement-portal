package com.fareye.expenseReimbursementPortal.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    private LocalDateTime timestamp;

    private String message;

    private int status;

    private String path;
}
