package org.example.Controllers;

import org.example.DAO.GroupDAO;
import org.example.DAO.GroupDAOImpl;
import org.example.models.DTO.LecturerDTO;
import org.example.models.DTO.StudentDTO;
import org.example.models.Grooup;
import org.example.models.Lecturer;
import org.example.models.Student;
import org.example.models.forAdmin.*;
import org.example.services.AdminService;
import org.example.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/groups")
    public ResponseEntity<List<Grooup>> getAllGroups() {
        List<Grooup> groups = adminService.getAllGrooups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = adminService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/lecturers")
    public ResponseEntity<List<LecturerDTO>> getAllLecturers() {
        List<LecturerDTO> lecturers = adminService.getAllLecturers();
        return ResponseEntity.ok(lecturers);
    }

    @PostMapping(value = "/group/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> AddGroupPage(@RequestBody AddFormGrooup form){
        adminService.create_updateGrooup(form);
        return  ResponseEntity.ok("Successfully");
    }
    @PostMapping(value = "/student/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> AddStudentPage(@RequestBody AddFormStudent form){
        adminService.create_updateStudent(form);
        return  ResponseEntity.ok("Successfully");
    }
    @PostMapping(value = "/lecturer/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> AddLecturerPage(@RequestBody AddFormLecturer form){
        adminService.create_updateLecturer(form);
        return  ResponseEntity.ok("Successfully");
    }

    @DeleteMapping("/group/delete/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        if (adminService.deleteGrooup(id)) {
            return ResponseEntity.ok("Group with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete group with ID " + id);
        }
    }
    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if (adminService.deleteStudent(id)) {
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete student with ID " + id);
        }
    }
    @DeleteMapping("/lecturer/delete/{id}")
    public ResponseEntity<String> deleteLecturer(@PathVariable Long id) {
        if (adminService.deleteLecturer(id)) {
            return ResponseEntity.ok("Lecturer with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete lecturer with ID " + id);
        }
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<Grooup> getGroupById(@PathVariable Long id) {
        Optional<Grooup> group = adminService.getGrooupById(id);
        return group.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = adminService.getStudentById(id);
        return ResponseEntity.ok(student);

    }
    @GetMapping("/lecturer/{id}")
    public ResponseEntity<LecturerDTO> getLecturerById(@PathVariable Long id) {
        LecturerDTO lecturer = adminService.getLecturerById(id);
        return ResponseEntity.ok(lecturer);
    }
}
