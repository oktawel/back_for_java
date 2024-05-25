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
    public ResponseEntity<List<TestDTO>> getAllTests() {
        List<TestDTO> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Long id) {
        TestDTO test = testService.getTestById(id);
        return ResponseEntity.ok(test);
    }
    @GetMapping("/test/open/{id}")
    public ResponseEntity<?> getOpenTestById(@PathVariable Long id) {
//        TestOpenDTO test = testService.getOpenTestById(id);
//        return ResponseEntity.ok(test);
        TestOpenDTO test = testService.getOpenTestById(id);
        if (test != null) {
            return ResponseEntity.ok(test);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The test is not available");
        }
    }
    @GetMapping("/testsByCourse/{subjectId}")
    public ResponseEntity<List<TestDTO>> getTestsBySubjectId(@PathVariable Long subjectId) {
        List<TestDTO> tests = testService.getTestsBySubjectId(subjectId);
        if (tests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tests);
    }

    @PostMapping(value = "/test/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> AddTestPage(@RequestBody AddFormTest form){
        System.out.println("Start");
        testService.create_updateTest(form);
        return  ResponseEntity.ok("Successfully");
    }

    @PostMapping(value = "/test/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> UpdateTestPage(@RequestBody AddFormTest form){
        testService.create_updateTest(form);
        return  ResponseEntity.ok("Successfully");
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
        testService.addAnswer(form);
        return  ResponseEntity.ok("Successfully");
    }

}
