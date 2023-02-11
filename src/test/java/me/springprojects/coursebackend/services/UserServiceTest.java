package me.springprojects.coursebackend.services;

import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.exceptions.UserNotFoundException;
import me.springprojects.coursebackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

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

    @Test
    public void createsUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test1");
        userDTO.setEmail("test1");
        userDTO.setPassword("test1");

        ResponseEntity<User> res = userService.createUser(userDTO);

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals(200, res.getStatusCode().value());
        assertEquals(userDTO.getUsername(), res.getBody().getUsername());
        assertEquals(userDTO.getEmail(), res.getBody().getEmail());
        assertEquals(userDTO.getPassword(), res.getBody().getPassword());
    }

    @Test
    public void changesUsername() throws UserNotFoundException{
        User user = new User();
        given(userRepository.findById((any()))).willReturn(Optional.of(user));

        ResponseEntity<User> res = userService.changeUserName(9999, "changedName");

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals(200, res.getStatusCode().value());
        assertEquals("changedName", res.getBody().getUsername());
    }

    @Test
    public void changesEmail() throws UserNotFoundException{
        User user = new User();
        given(userRepository.findById((any()))).willReturn(Optional.of(user));

        ResponseEntity<User> res = userService.changeUserMail(9999, "changedMail");

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals(200, res.getStatusCode().value());
        assertEquals("changedMail", res.getBody().getEmail());
    }

    @Test
    public void changesPassword() throws UserNotFoundException{
        User user = new User();
        given(userRepository.findById((any()))).willReturn(Optional.of(user));

        ResponseEntity<User> res = userService.changeUserPassword(9999, "changedPassword");

        verify(userRepository, times(1)).save(any());
        verify(emailService, times(1)).sendMessage(any(), any(), any());
        assertEquals(200, res.getStatusCode().value());
        assertEquals("changedPassword", res.getBody().getPassword());
    }

    @Test
    public void throwsExceptionWhenUserWithIdNotFound(){
        final int userId = Integer.MAX_VALUE;

        assertThrows(UserNotFoundException.class, () -> userService.changeUserName(userId, ""));
        assertThrows(UserNotFoundException.class, () -> userService.changeUserPassword(userId, ""));
        assertThrows(UserNotFoundException.class, () -> userService.changeUserMail(userId, ""));
    }

}