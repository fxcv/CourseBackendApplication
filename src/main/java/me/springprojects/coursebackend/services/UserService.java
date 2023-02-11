package me.springprojects.coursebackend.services;

import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.exceptions.UserNotFoundException;
import me.springprojects.coursebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<User> createUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCourses(new ArrayList<>());
        user.setCoursesCreated(new ArrayList<>());
        userRepository.save(user);
        final String subject = "Account creation";
        final String text = "Hi " + userDTO.getUsername() + ", thank you for creating an account on our website.";
        emailService.sendMessage(userDTO.getEmail(), subject, text);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> changeUserName(int userId, String username) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User with id " + userId + " has not been found.");
        User user = userOptional.get();
        user.setUsername(username);
        userRepository.save(user);
        final String subject = "Username changed";
        final String text = "Hi, you have changed your username to: " + username;
        emailService.sendMessage(user.getEmail(), subject, text);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> changeUserMail(int userId, String email) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User with id " + userId + " has not been found.");
        User user = userOptional.get();
        String oldEmail = user.getEmail();
        user.setEmail(email);
        userRepository.save(user);
        final String subject = "Email changed";
        final String text = "Hi, you have changed your email to: " + email + ", this email will no longer be associated with your account.";
        emailService.sendMessage(oldEmail, subject, text);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> changeUserPassword(int userId, String password) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User with id " + userId + " has not been found.");
        User user = userOptional.get();
        user.setPassword(password);
        userRepository.save(user);
        final String subject = "Password changed";
        final String text = "Hi, you have changed your password to: " + password;
        emailService.sendMessage(user.getEmail(), subject, text);
        return ResponseEntity.ok(user);
    }

}
