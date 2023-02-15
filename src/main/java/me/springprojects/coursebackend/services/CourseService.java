package me.springprojects.coursebackend.services;

import me.springprojects.coursebackend.entities.Course;
import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.CourseDTO;
import me.springprojects.coursebackend.entities.enums.CourseCategory;
import me.springprojects.coursebackend.exceptions.UserNotFoundException;
import me.springprojects.coursebackend.repositories.CourseRepository;
import me.springprojects.coursebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    public Course createCourse(CourseDTO courseDTO, int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User with id " + userId + " has not been found.");
        User user = userOptional.get();
        Course course = new Course();
        course.setCourseOwner(user);
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseCategory(CourseCategory.valueOf(courseDTO.getCourseCategory()));
        course.setUsers(new ArrayList<>());
        user.getCoursesCreated().add(course);
        courseRepository.save(course);
        userRepository.save(user);
        final String subject = "Course created";
        final String text = "You have successfully created a course named " + courseDTO.getCourseName() + " in the " + courseDTO.getCourseCategory().toLowerCase() + " category!";
        emailService.sendMessage(user.getEmail(), subject, text);
        return course;
    }

    public List<CourseDTO> getCourses(){
        return courseRepository.findAll().stream()
                .map(course -> {
                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setCourseName(course.getCourseName());
                    courseDTO.setCourseCategory(course.getCourseCategory().toString());
                    return courseDTO;
                })
                .collect(Collectors.toList());
    }
}
