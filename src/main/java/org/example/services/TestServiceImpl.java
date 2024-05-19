package org.example.services;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.GroupDTOForCourse;
import org.example.models.DTO.TestDTO;
import org.example.models.Grooup;
import org.example.models.Subject;
import org.example.models.Test;
import org.example.models.forms.AddFormCourse;
import org.example.models.forms.UpdateFormCourse;
import org.example.repository.CourseRepository;
import org.example.repository.GroupRepository;
import org.example.repository.LecturerRepository;
import org.example.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    public List<TestDTO> getTestsBySubjectId(Long subjectId) {
        List<Test> tests = testRepository.findBySubjectId(subjectId);
        return initializeTestDTOs(tests);
    }
    private TestDTO initializeTestDTO(Test test) {
        TestDTO dto = new TestDTO();
        dto.setId(test.getId());
        dto.setName(test.getName());
        dto.setDescription(test.getDescription());
        dto.setOpen(test.isOpen());
        dto.setSubjectId(test.getSubject().getId());
        return dto;
    }

    private List<TestDTO> initializeTestDTOs(List<Test> tests) {
        List<TestDTO> testDTOs = new ArrayList<>();
        for (Test test : tests) {
            testDTOs.add(initializeTestDTO(test));
        }
        return testDTOs;
    }
}
