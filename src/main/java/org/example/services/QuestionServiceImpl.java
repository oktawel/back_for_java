package org.example.services;

import org.example.models.*;
import org.example.models.DTO.QuestionDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.forms.AddFormOption;
import org.example.models.forms.AddFormQuestion;
import org.example.models.forms.AddFormTest;
import org.example.repository.CourseRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.TestRepository;
import org.example.repository.TypeQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TypeQuestionRepository typeQuestionRepository;
    @Autowired
    private OptionService optionService;
    @Override
    public List<QuestionDTO> getQuestionsByTestId(Long testId) {
        System.out.println("Question");
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = questionRepository.findByTestId(testId);
        for (Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setTypeQuestionID(question.getType().getId());
            questionDTO.setTestId(question.getTest().getId());
            questionDTO.setText(question.getText());
            questionDTO.setCost(question.getCost());
            questionDTO.setOptions(optionService.getOptionsByQuestionId(question.getType().getId(), question.getId()));
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    @Override
    public boolean addQuestions(Long testId,  List<AddFormQuestion> questions){
        try {
            for (AddFormQuestion questionForm: questions){
                Optional<Test> optionalTest = testRepository.findById(testId);
                if (optionalTest.isPresent()){
                    Test test = optionalTest.get();
                    Question question = new Question();
                    question.setTest(test);
                    question.setCost(questionForm.getCost());
                    question.setText(questionForm.getText());
                    question.setType(typeQuestionRepository.findById(questionForm.getTypeId()).get());
                    if (questionForm.getId() != null){
                        question.setId(questionForm.getId());
                    }
                    Long qiestionId = questionRepository.saveAndReturnId(question);
                    System.out.println("Question add");
                    optionService.addOptions(qiestionId, questionForm.getAddFormOptionList());
                }
                else {
                    return false;
                }
            }

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public boolean deleteQuestions(Long testId){
        try {
            List<Question> questions = questionRepository.findByTestId(testId);
            if (!questions.isEmpty()){
                for (Question question: questions){
                    optionService.deleteOption(question.getType().getId(),question.getId());
                    questionRepository.delete(question);
                }
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
