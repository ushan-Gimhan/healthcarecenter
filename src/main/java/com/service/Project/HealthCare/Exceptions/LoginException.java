package com.service.Project.HealthCare.Exceptions;

public class LoginException extends ApplicationException{
    public LoginException(String message) {
        super("Login Error: " + message);
    }
}
