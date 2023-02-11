package me.springprojects.coursebackend.services;

import me.springprojects.coursebackend.entities.Course;
import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.CourseDTO;
import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.entities.enums.CourseCategory;
import me.springprojects.coursebackend.exceptions.UserNotFoundException;
import me.springprojects.coursebackend.repositories.CourseRepository;
import me.springprojects.coursebackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private EmailService emailService;

    @Test
    public void createsCourse() throws UserNotFoundException {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("testCourse");
        courseDTO.setCourseCategory("PROGRAMMING");
        User user = new User();
        user.setCoursesCreated(new ArrayList<>());
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        Course res = courseService.createCourse(courseDTO, 9999);

        verify(courseRepository, times(1)).save(any());
        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals(courseDTO.getCourseName(), res.getCourseName());
        assertEquals(courseDTO.getCourseCategory(), res.getCourseCategory().toString());
        assertEquals(user, res.getCourseOwner());
        assertEquals(1, user.getCoursesCreated().size());
    }

    @Test
    public void getsCourses(){
        Course course1 = new Course();
        course1.setCourseName("course1");
        course1.setCourseCategory(CourseCategory.MATH);
        Course course2 = new Course();
        course2.setCourseName("course2");
        course2.setCourseCategory(CourseCategory.MUSIC);
        List<Course> courses = List.of(course1, course2);
        given(courseRepository.findAll()).willReturn(courses);

        List<CourseDTO> res = courseService.getCourses();

        assertEquals("course1", res.get(0).getCourseName());
        assertEquals("course2", res.get(1).getCourseName());
        assertEquals("MATH", res.get(0).getCourseCategory().toString());
        assertEquals("MUSIC", res.get(1).getCourseCategory().toString());
    }

}