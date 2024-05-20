package org.example.services;

import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.GroupDTOForCourse;
import org.example.models.DTO.TestDTO;
import org.example.models.Grooup;
import org.example.models.Subject;
import org.example.models.Test;
import org.example.models.forms.AddFormCourse;
import org.example.models.forms.AddFormTest;
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
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<TestDTO> getTestsBySubjectId(Long subjectId) {
        List<Test> tests = testRepository.findBySubjectId(subjectId);
        return initializeTestDTOs(tests);
    }

    @Override
    public boolean create_updateTest(AddFormTest form){
        Test test = new Test();
        Long id;

        if(form.getId() != null){
            id = form.getId();
            test.setId(id);
        }
        test.setName(form.getName());
        test.setDescription(form.getDescription());
        test.setOpen(form.isOpen());
        test.setSubject(courseRepository.findById(form.getSubjectId()).get());
        if (saveTest(test)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTest(Long id){
        try {
            Optional<Test> testOptional = testRepository.findById(id);
            if (testOptional.isPresent()) {
                Test test = testOptional.get();
//                for (Grooup group : course.getGroups()) {
//                    group.getSubjects().remove(course);
//                    groupRepository.save(group);
//                }
                testRepository.delete(test);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TestDTO getTestById(Long id){
        return initializeTestDTO(testRepository.findById(id).get());
    }
    @Override
    public List<TestDTO> getAllTests(){
        List<Test> tests = testRepository.findAll();
        return initializeTestDTOs(tests);
    }

    private boolean saveTest(Test test) {
        try {
            testRepository.save(test);
            return false;
        } catch (Exception e) {
            return true;
        }
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
