package org.example.Controllers;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.DTO.TestOpenDTO;
import org.example.models.forms.AddFormAnswerQuestion;
import org.example.models.forms.AddFormAnswerTest;
import org.example.models.forms.AddFormCourse;
import org.example.models.forms.AddFormTest;
import org.example.services.CourseService;
import org.example.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/tests")
    public ResponseEntity<?> getAllTests(@RequestParam Long studentId) {
        List<TestDTO> tests = testService.getAllTests(studentId);
        if (tests == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tests not found");
        }
        else{
            return ResponseEntity.ok(tests);
        }
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> getTestById(@RequestParam Long studentId, @PathVariable Long id) {
        TestDTO test = testService.getTestById(studentId, id);
        if (test == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test not found");
        }
        else{
            return ResponseEntity.ok(test);
        }
    }
    @GetMapping("/test/open/{id}")
    public ResponseEntity<?> getOpenTestById(@PathVariable Long id) {
        TestOpenDTO test = testService.getOpenTestById(id);
        if (test != null) {
            return ResponseEntity.ok(test);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The test is not available");
        }
    }    @GetMapping("/testsByCourse/{subjectId}")
    public ResponseEntity<?> getTestsBySubjectId(@RequestParam Long studentId, @PathVariable Long subjectId) {
        List<TestDTO> tests = testService.getTestsBySubjectId(studentId, subjectId);
        if (tests == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tests not found");
        }
        else{
            return ResponseEntity.ok(tests);
        }

    }

    @PostMapping(value = "/test/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> AddTestPage(@RequestBody AddFormTest form){
        System.out.println("Start");
        if (testService.create_updateTest(form)) {
            return  ResponseEntity.ok("Successfully");
        }
        else {
            return  ResponseEntity.badRequest().body("Error adding");
        }
    }

    @PostMapping(value = "/test/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> UpdateTestPage(@RequestBody AddFormTest form){
        if (testService.create_updateTest(form)) {
            return  ResponseEntity.ok("Successfully");
        }
        else {
            return  ResponseEntity.badRequest().body("Update error");
        }
    }

    @DeleteMapping("/test/delete/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id) {
        if (testService.deleteTest(id)) {
            return ResponseEntity.ok("Test with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete test with ID " + id);
        }
    }

    @PostMapping(value = "/test/answer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> AnswerTestPage(@RequestBody AddFormAnswerTest form){
        if (testService.addAnswer(form)) {
            return  ResponseEntity.ok("Successfully");
        }
        else {
            return  ResponseEntity.badRequest().body("Error adding answers");
        }
    }

}
