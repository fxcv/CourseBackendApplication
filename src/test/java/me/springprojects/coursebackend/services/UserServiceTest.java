package me.springprojects.coursebackend.services;

import me.springprojects.coursebackend.entities.Course;
import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.exceptions.CourseNotFoundException;
import me.springprojects.coursebackend.exceptions.UserNotFoundException;
import me.springprojects.coursebackend.repositories.CourseRepository;
import me.springprojects.coursebackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EmailService emailService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    public void createsUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test1");
        userDTO.setEmail("test1");
        userDTO.setPassword("test1");

        User res = userService.createUser(userDTO);

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals(userDTO.getUsername(), res.getUsername());
        assertEquals(userDTO.getEmail(), res.getEmail());
        assertEquals(userDTO.getPassword(), res.getPassword());
    }

    @Test
    public void getsUsers(){
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1email");
        user1.setPassword("user1password");
        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user2email");
        user2.setPassword("user2password");
        List<User> users = List.of(user1, user2);
        given(userRepository.findAll()).willReturn(users);

        List<UserDTO> res = userService.getUsers();

        assertEquals(2, res.size());
        assertEquals("user1", res.get(0).getUsername());
        assertEquals("user2", res.get(1).getUsername());
        assertEquals("user1email", res.get(0).getEmail());
        assertEquals("user2email", res.get(1).getEmail());
        assertEquals("user1password", res.get(0).getPassword());
        assertEquals("user2password", res.get(1).getPassword());
    }

    @Test
    public void addUserToCourse() throws UserNotFoundException, CourseNotFoundException{
        User user = new User();
        user.setCourses(new ArrayList<>());
        Course course = new Course();
        course.setUsers(new ArrayList<>());
        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(courseRepository.findById(any())).willReturn(Optional.of(course));

        userService.addUserToCourse(999, 999);

        verify(userRepository, times(1)).save(any());
        verify(courseRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertTrue(user.getCourses().contains(course));
        assertTrue(course.getUsers().contains(user));
    }

    @Test
    public void deletesUserFromCourse() throws UserNotFoundException, CourseNotFoundException{
        User user = new User();
        user.setCourses(new ArrayList<>());
        Course course = new Course();
        course.setUsers(new ArrayList<>());
        user.getCourses().add(course);
        course.getUsers().add(user);
        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(courseRepository.findById(any())).willReturn(Optional.of(course));

        userService.deleteUserFromCourse(999, 999);

        verify(userRepository, times(1)).save(any());
        verify(courseRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertFalse(user.getCourses().contains(course));
        assertFalse(course.getUsers().contains(user));
    }

    @Test
    public void changesUsername() throws UserNotFoundException{
        User user = new User();
        given(userRepository.findById((any()))).willReturn(Optional.of(user));

        User res = userService.changeUserName(9999, "changedName");

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals("changedName", res.getUsername());
    }

    @Test
    public void changesEmail() throws UserNotFoundException{
        User user = new User();
        given(userRepository.findById((any()))).willReturn(Optional.of(user));

        User res = userService.changeUserMail(9999, "changedMail");

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals("changedMail", res.getEmail());
    }

    @Test
    public void changesPassword() throws UserNotFoundException{
        User user = new User();
        given(userRepository.findById((any()))).willReturn(Optional.of(user));

        User res = userService.changeUserPassword(9999, "changedPassword");

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals("changedPassword", res.getPassword());
    }

    @Test
    public void throwsExceptionWhenUserWithIdNotFound(){
        final int userId = Integer.MAX_VALUE;

        assertThrows(UserNotFoundException.class, () -> userService.changeUserName(userId, ""));
        assertThrows(UserNotFoundException.class, () -> userService.changeUserPassword(userId, ""));
        assertThrows(UserNotFoundException.class, () -> userService.changeUserMail(userId, ""));
    }

    @Test
    public void throwsExceptionWhenCourseWithIdNotFound(){
        final int courseId = Integer.MAX_VALUE;
        given(userRepository.findById(any())).willReturn(Optional.of(new User()));

        assertThrows(CourseNotFoundException.class, () -> userService.addUserToCourse(9999, courseId));
    }

}