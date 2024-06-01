package org.example.Controllers;

import org.example.models.DTO.GroupDTO;
import org.example.models.DTO.LecturerDTO;
import org.example.models.DTO.StudentDTO;
import org.example.models.Grooup;
import org.example.models.forms.*;
import org.example.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('Admin')")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/lecturersByName")
    public ResponseEntity<List<LecturerDTO>> getAllLecturersByName(@RequestParam String name) {
        List<LecturerDTO> lecturerDTOList = adminService.getLecturersByName(name);
        return ResponseEntity.ok(lecturerDTOList);
    }
    @GetMapping("/lecturersBySurname")
    public ResponseEntity<List<LecturerDTO>> getAllLecturersBySurname(@RequestParam String surname) {
        List<LecturerDTO> lecturerDTOList = adminService.getLecturersBySurname(surname);
        return ResponseEntity.ok(lecturerDTOList);
    }
    @GetMapping("/studentsByName")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByName(@RequestParam String name) {
        List<StudentDTO> studentDTOList = adminService.getStudentsByName(name);
        return ResponseEntity.ok(studentDTOList);
    }
    @GetMapping("/studentsBySurname")
    public ResponseEntity<List<StudentDTO>> getAllStudentsBySurname(@RequestParam String surname) {
        List<StudentDTO> studentDTOList = adminService.getStudentsBySurname(surname);
        return ResponseEntity.ok(studentDTOList);
    }
    @GetMapping("/studentsByGroup")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByGroup(@RequestParam Long id) {
        List<StudentDTO> studentDTOList = adminService.getStudentsByGroup(id);
        return ResponseEntity.ok(studentDTOList);
    }
    @GetMapping("/groupsByName")
    public ResponseEntity<List<GroupDTO>> getAllGroupsByName(@RequestParam String name) {
        List<GroupDTO> groupDTOList = adminService.getGrooupsByName(name);
        return ResponseEntity.ok(groupDTOList);
    }








    @GetMapping("/groups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = adminService.getAllGrooups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = adminService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/lecturers")
    @PreAuthorize("hasRole('ROLE_Admin')")
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
    @PostMapping(value = "/group/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> UpdateGroupPage(@RequestBody AddFormGrooup form){
        adminService.create_updateGrooup(form);
        return  ResponseEntity.ok("Successfully");
    }
    @PostMapping(value = "/student/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> UpdateStudentPage(@RequestBody AddFormStudent form){
        adminService.create_updateStudent(form);
        return  ResponseEntity.ok("Successfully");
    }
    @PostMapping(value = "/lecturer/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> UpdateLecturerPage(@RequestBody AddFormLecturer form){
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
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        GroupDTO group = adminService.getGrooupById(id);
        return ResponseEntity.ok(group);
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
