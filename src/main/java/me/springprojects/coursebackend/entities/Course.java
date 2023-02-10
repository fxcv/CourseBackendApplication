package me.springprojects.coursebackend.entities;

import jakarta.persistence.*;
import me.springprojects.coursebackend.entities.enums.CourseCategory;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "course_owner")
    @ManyToOne
    private User courseOwner;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_creation_date")
    private LocalDateTime courseCreationDate;

    @Column(name = "course_category")
    @Enumerated(value = EnumType.STRING)
    private CourseCategory courseCategory;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCourseOwner() {
        return courseOwner;
    }

    public void setCourseOwner(User courseOwner) {
        this.courseOwner = courseOwner;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getCourseCreationDate() {
        return courseCreationDate;
    }

    public void setCourseCreationDate(LocalDateTime courseCreationDate) {
        this.courseCreationDate = courseCreationDate;
    }

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
