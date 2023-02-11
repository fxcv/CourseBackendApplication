package me.springprojects.coursebackend.entities.dto;

import java.time.LocalDateTime;

public class CourseDTO {

    private String courseName;
    private String courseCategory;
    private LocalDateTime courseCreationDate;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public LocalDateTime getCourseCreationDate() {
        return courseCreationDate;
    }

    public void setCourseCreationDate(LocalDateTime courseCreationDate) {
        this.courseCreationDate = courseCreationDate;
    }
}
