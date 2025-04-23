package com.service.Project.HealthCare.Exceptions;

public class PaymentProcessingException extends ApplicationException {
    public PaymentProcessingException(String message) {
        super("Payment Processing Error: " + message);
    }
}
