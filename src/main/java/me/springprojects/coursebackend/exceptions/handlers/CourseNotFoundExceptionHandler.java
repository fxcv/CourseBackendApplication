package me.springprojects.coursebackend.exceptions.handlers;

import me.springprojects.coursebackend.exceptions.CourseNotFoundException;
import me.springprojects.coursebackend.exceptions.dto.CourseNotFoundExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class CourseNotFoundExceptionHandler {

    @ExceptionHandler(value = {CourseNotFoundException.class})
    public ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CourseNotFoundExceptionDTO res = new CourseNotFoundExceptionDTO(
                ex.getMessage(),
                ZonedDateTime.now(),
                status
        );
        return new ResponseEntity<>(res, status);
    }
}
