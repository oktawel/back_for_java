package org.example.services;

import org.example.models.*;
import org.example.models.DTO.CourseDTO;
import org.example.models.DTO.GroupDTOForCourse;
import org.example.models.DTO.TestDTO;
import org.example.models.DTO.TestOpenDTO;
import org.example.models.forms.*;
import org.example.repository.*;
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
    private ResultTestRepository resultTestRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionService questionService;
    @Override
    public List<TestDTO> getTestsBySubjectId(Long subjectId) {
        List<Test> tests = testRepository.findBySubjectId(subjectId);
        return initializeTestDTOs(tests);
    }

    @Override
    public boolean create_updateTest(AddFormTest form){
        try {
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
            Long testId = testRepository.saveAndReturnId(test);
            questionService.addQuestions(testId, form.getAddFormQuestionList());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteTest(Long id){
        try {
            Optional<Test> testOptional = testRepository.findById(id);
            if (testOptional.isPresent()) {
                Test test = testOptional.get();
                questionService.deleteQuestions(test.getId());
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
    @Override
    public TestOpenDTO getOpenTestById(Long id){
        return initializeTestOpenDTO(testRepository.findById(id).get());
    }


    private boolean saveTest(Test test) {
        try {
            testRepository.save(test);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean addAnswer(AddFormAnswerTest test){
        ResultTest resultTest = new ResultTest();
        try {
            resultTest.setTest(testRepository.findById(test.getTestId()).get());
            resultTest.setStudent(studentRepository.findById(test.getStudentId()).get());
            Long resultTestId = resultTestRepository.saveAndReturnId(resultTest);
            for(AddFormAnswerQuestion question: test.getAnswerQuestions()){
               questionService.addAnswerQuestion(resultTestId, question);
            }
            return true;
        }
        catch (Exception e){
            return false;
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

    private TestOpenDTO initializeTestOpenDTO(Test test) {
        TestOpenDTO dto = new TestOpenDTO();
        dto.setId(test.getId());
        dto.setName(test.getName());
        dto.setDescription(test.getDescription());
        dto.setOpen(test.isOpen());
        dto.setSubjectId(test.getSubject().getId());
        dto.setQuestions(questionService.getQuestionsByTestId(test.getId()));
        return dto;
    }

    private List<TestOpenDTO> initializeTestOpenDTOs(List<Test> tests) {
        List<TestOpenDTO> testOpenDTOs = new ArrayList<>();
        for (Test test : tests) {
            testOpenDTOs.add(initializeTestOpenDTO(test));
        }
        return testOpenDTOs;
    }
}
