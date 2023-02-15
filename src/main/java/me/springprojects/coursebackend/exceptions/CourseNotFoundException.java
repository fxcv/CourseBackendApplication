package me.springprojects.coursebackend.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(String message){
        super(message);
    }
}
