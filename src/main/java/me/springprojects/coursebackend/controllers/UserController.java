package me.springprojects.coursebackend.controllers;

import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.exceptions.UserNotFoundException;
import me.springprojects.coursebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PutMapping("/change/username")
    public ResponseEntity<User> changeUserName(@RequestParam(name = "id") int userId, @RequestParam(name = "username") String username) throws UserNotFoundException {
        return userService.changeUserName(userId, username);
    }

    @PutMapping("/change/email")
    public ResponseEntity<User> changeUserMail(@RequestParam(name = "id") int userId, @RequestParam(name = "email") String email) throws UserNotFoundException {
        return userService.changeUserMail(userId, email);
    }

    @PutMapping("/change/password")
    public ResponseEntity<User> changeUserPassword(@RequestParam(name = "id") int userId, @RequestParam(name = "password") String password) throws UserNotFoundException {
        return userService.changeUserPassword(userId, password);
    }
}
