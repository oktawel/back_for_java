package org.example.services;

import org.example.models.*;
import org.example.models.DTO.QuestionDTO;
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
        return initializeTestDTOs(1L,tests);
    }

    @Override
    public boolean create_updateTest(AddFormTest form){
        try {
            Test test = new Test();
            Long testId;
            test.setName(form.getName());
            test.setDescription(form.getDescription());
            test.setOpen(form.isOpen());
            test.setSubject(courseRepository.findById(form.getSubjectId()).get());
            if(form.getId() != null){
                testId = form.getId();
                test.setId(testId);
                saveTest(test);
            }
            else {
                testId = testRepository.saveAndReturnId(test);
                questionService.addQuestions(testId, form.getAddFormQuestionList());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean updateTest(AddFormTest form){
        try {
            Test test = new Test();
            test.setId(form.getId());
            test.setName(form.getName());
            test.setDescription(form.getDescription());
            test.setOpen(form.isOpen());
            test.setSubject(courseRepository.findById(form.getSubjectId()).get());
            saveTest(test);
            return true;
        } catch (Exception e) {
            System.out.println(e);
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
        return initializeTestDTO(1L, testRepository.findById(id).get());
    }
    @Override
    public List<TestDTO> getAllTests(){
        List<Test> tests = testRepository.findAll();
        return initializeTestDTOs(1L, tests);
    }
    @Override
    public TestOpenDTO getOpenTestById(Long id){
        TestOpenDTO test = initializeTestOpenDTO(testRepository.findById(id).get());
        if (!test.isOpen()) {
            return null;
        }
        return test;
    }


    private boolean saveTest(Test test) {
        try {
            System.out.println("save");
            testRepository.save(test);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }

    @Override
    public boolean addAnswer(AddFormAnswerTest test){
        ResultTest resultTest = new ResultTest();
        double points = 0;
        try {
            Test testInfo = testRepository.findById(test.getTestId()).get();
            Student studentInfo = studentRepository.findById(test.getStudentId()).get();

            resultTest.setTest(testInfo);
            resultTest.setStudent(studentInfo);

            Long resultTestId = resultTestRepository.saveAndReturnId(resultTest);
            for(AddFormAnswerQuestion question: test.getAnswerQuestions()){
                points += questionService.addAnswerQuestion(resultTestId, question);
            }

            List<QuestionDTO> questions = questionService.getQuestionsByTestId(test.getTestId());
            int maxPoints = 0;
            int countQuestion = 0;
            for(QuestionDTO question: questions){
                maxPoints += question.getCost();
                countQuestion += 1;
            }
            ResultTest result = resultTestRepository.findById(resultTestId).get();
            Double mark = (points / (maxPoints / countQuestion)) * 2;
            result.setMark(mark);
            resultTestRepository.save(result);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }



    private TestDTO initializeTestDTO(Long studentId, Test test) {
        TestDTO dto = new TestDTO();
        dto.setId(test.getId());
        dto.setName(test.getName());
        dto.setDescription(test.getDescription());
        dto.setOpen(test.isOpen());
        dto.setSubjectId(test.getSubject().getId());

        Optional<ResultTest> optionalResultTest = resultTestRepository.findByStudentIdAndTestId(studentId, test.getId());
        if (optionalResultTest.isPresent()){
            dto.setMark(optionalResultTest.get().getMark());
        }
        else {
            dto.setMark(null);
        }
        return dto;
    }

    private List<TestDTO> initializeTestDTOs(Long studentId, List<Test> tests) {
        List<TestDTO> testDTOs = new ArrayList<>();
        for (Test test : tests) {
            testDTOs.add(initializeTestDTO(studentId, test));
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
