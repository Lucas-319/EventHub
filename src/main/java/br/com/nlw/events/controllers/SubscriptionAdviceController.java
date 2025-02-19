package br.com.nlw.events.controllers;

import br.com.nlw.events.dtos.ErrorMessage;
import br.com.nlw.events.exceptions.EventNotFoundException;
import br.com.nlw.events.exceptions.SubscriptionConflictException;
import br.com.nlw.events.exceptions.UserIndicadorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubscriptionAdviceController {

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

    @ExceptionHandler(UserIndicadorNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage eventNotFoundException(UserIndicadorNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }
}
