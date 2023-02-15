package me.springprojects.coursebackend.exceptions.handlers;

import me.springprojects.coursebackend.exceptions.UserNotFoundException;
import me.springprojects.coursebackend.exceptions.dto.UserNotFoundExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        UserNotFoundExceptionDTO res = new UserNotFoundExceptionDTO(
                ex.getMessage(),
                ZonedDateTime.now(),
                status
        );
        return new ResponseEntity<>(res, status);
    }
}
