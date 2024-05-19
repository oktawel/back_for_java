package org.example.Controllers;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.LecturerDTO;
import org.example.models.DTO.StudentDTO;
import org.example.models.Grooup;
import org.example.models.forms.*;
import org.example.services.AdminService;
import org.example.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courses = courseService.getCourseById(id);
        return ResponseEntity.ok(courses);
    }

    @PostMapping(value = "/course/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> AddCoursePage(@RequestBody AddFormCourse form){
        courseService.create_updateCourse(form);
        return  ResponseEntity.ok("Successfully");
    }

    @GetMapping(value = "/course/{courseId}/connectGroup/{groupId}")
    public ResponseEntity<String> ConnectGroupCoursePage(@PathVariable Long courseId, @PathVariable Long groupId){
        courseService.addGroupToCourse(courseId, groupId);
        return  ResponseEntity.ok("Successfully");
    }
    @GetMapping(value = "/course/{courseId}/removeConnect/{groupId}")
    public ResponseEntity<String> RemoveConnectGroupCoursePage(@PathVariable Long courseId, @PathVariable Long groupId){
        courseService.removeGroupFromCourse(courseId, groupId);
        return  ResponseEntity.ok("Successfully");
    }

    @PostMapping(value = "/course/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> UpdateCoursePage(@RequestBody AddFormCourse form){
        courseService.create_updateCourse(form);
        return  ResponseEntity.ok("Successfully");
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.ok("Course with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete course with ID " + id);
        }
    }
}
