package me.springprojects.coursebackend.controllers;

import me.springprojects.coursebackend.entities.dto.UserDTO;
import me.springprojects.coursebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
    }

    @GetMapping("/get")
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/checkLogin")
    public boolean checkIfCorrectLogin(@RequestParam(name = "uname") String username, @RequestParam(name = "uemail") String email){
        return userService.checkIfCorrectLogin(username, email);
    }

    @PutMapping("/addToCourse")
    public void addUserToCourse(@RequestParam(name = "uid") int userId, @RequestParam(name = "cid") int courseId)  {
        userService.addUserToCourse(userId, courseId);
    }

    @DeleteMapping("/deleteFromCourse")
    public void deleteUserFromCourse(@RequestParam(name = "uid") int userId, @RequestParam(name = "cid") int courseId)  {
        userService.deleteUserFromCourse(userId, courseId);
    }

    @PutMapping("/change/username")
    public void changeUserName(@RequestParam(name = "id") int userId, @RequestParam(name = "username") String username) {
        userService.changeUserName(userId, username);
    }

    @PutMapping("/change/email")
    public void changeUserMail(@RequestParam(name = "id") int userId, @RequestParam(name = "email") String email)  {
        userService.changeUserMail(userId, email);
    }

    @PutMapping("/change/password")
    public void changeUserPassword(@RequestParam(name = "id") int userId, @RequestParam(name = "password") String password)  {
        userService.changeUserPassword(userId, password);
    }
}
