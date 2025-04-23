package com.service.Project.HealthCare.Exceptions;

public class SchedulingConflictException extends ApplicationException{
    public SchedulingConflictException(String message) {
        super("Scheduling Conflict : " + message);
    }
}
