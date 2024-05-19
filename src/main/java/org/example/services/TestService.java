package org.example.services;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.forms.AddFormCourse;

import java.util.List;

public interface TestService {
    //boolean create_updateTest(AddFormCourse form);
//    boolean deleteTest(Long id);
//    TestDTO getTestById(Long id);
//    List<TestDTO> getAllTests();
    List<TestDTO> getTestsBySubjectId(Long subjectId);

}
