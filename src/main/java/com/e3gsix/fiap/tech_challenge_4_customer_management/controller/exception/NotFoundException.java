package com.e3gsix.fiap.tech_challenge_4_customer_management.controller.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
