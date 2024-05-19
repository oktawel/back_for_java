package org.example.Controllers;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.forms.AddFormCourse;
import org.example.services.CourseService;
import org.example.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/testsByCourse/{subjectId}")
    public ResponseEntity<List<TestDTO>> getTestsBySubjectId(@PathVariable Long subjectId) {
        List<TestDTO> tests = testService.getTestsBySubjectId(subjectId);
        if (tests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tests);
    }
}
