package org.example.services;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.DTO.TestOpenDTO;
import org.example.models.forms.AddFormAnswerTest;
import org.example.models.forms.AddFormCourse;
import org.example.models.forms.AddFormTest;

import java.util.List;

public interface TestService {
    boolean create_updateTest(AddFormTest form);
    boolean deleteTest(Long id);
    TestDTO getTestById(Long id);
    List<TestDTO> getAllTests();
    List<TestDTO> getTestsBySubjectId(Long subjectId);
    TestOpenDTO getOpenTestById(Long id);
    boolean addAnswer(AddFormAnswerTest test);
}