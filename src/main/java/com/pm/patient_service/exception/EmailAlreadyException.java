package com.pm.patient_service.exception;

public class EmailAlreadyException extends RuntimeException {
    public EmailAlreadyException(String message) {
        super(message);
    }
}
