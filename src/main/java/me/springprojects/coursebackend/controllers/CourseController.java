package me.springprojects.coursebackend.controllers;

import me.springprojects.coursebackend.entities.dto.CourseDTO;
import me.springprojects.coursebackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public void createCourse(@RequestBody CourseDTO courseDTO, @RequestParam(name = "id") int userId) {
        courseService.createCourse(courseDTO, userId);
    }

    @GetMapping("/get")
    public List<CourseDTO> getCourses(){
        return courseService.getCourses();
    }
}
