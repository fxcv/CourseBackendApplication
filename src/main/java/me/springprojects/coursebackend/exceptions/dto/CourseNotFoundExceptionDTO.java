package me.springprojects.coursebackend.exceptions.dto;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CourseNotFoundExceptionDTO {

    private final String message;
    private final ZonedDateTime zonedDateTime;
    private final HttpStatus httpStatus;

    public CourseNotFoundExceptionDTO(String message, ZonedDateTime zonedDateTime, HttpStatus httpStatus) {
        this.message = message;
        this.zonedDateTime = zonedDateTime;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
