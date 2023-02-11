package me.springprojects.coursebackend.services;

import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        userRepository.save(user);
        final String subject = "Account creation";
        final String text = "Hi " + userDTO.getUsername() + ", thank you for creating an account on our website.";
        emailService.sendMessage(userDTO.getEmail(), subject, text);
        return ResponseEntity.ok(user);
    }

}
