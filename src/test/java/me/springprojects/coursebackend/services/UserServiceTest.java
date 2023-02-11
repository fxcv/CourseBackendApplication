package me.springprojects.coursebackend.services;

import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.UserDTO;
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
        assertEquals(userDTO.getUsername(), res.getBody().getUsername());
        assertEquals(userDTO.getEmail(), res.getBody().getEmail());
        assertEquals(userDTO.getPassword(), res.getBody().getPassword());
    }

}