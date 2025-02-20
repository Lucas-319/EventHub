package br.com.nlw.events.controllers;

import br.com.nlw.events.dtos.ErrorMessage;
import br.com.nlw.events.exceptions.EventNotFoundException;
import br.com.nlw.events.exceptions.SubscriptionConflictException;
import br.com.nlw.events.exceptions.UserIndicatorNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage eventNotFoundException(EventNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(SubscriptionConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage eventNotFoundException(SubscriptionConflictException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(UserIndicatorNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage eventNotFoundException(UserIndicatorNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage duplicateEntryInDataBase(Exception e) {
        return new ErrorMessage("The data you are trying to send already exists in the database.");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundException(EntityNotFoundException e) {
        return new ErrorMessage("The entity you are trying to send does not exist in the database.");
    }
}
