package me.springprojects.coursebackend.controllers;

import me.springprojects.coursebackend.entities.User;
import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
}
