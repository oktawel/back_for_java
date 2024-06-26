package org.example.services;

import org.example.models.*;
import org.example.models.DTO.QuestionDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.forms.*;
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
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = questionRepository.findByTestId(testId);
        try {
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
        catch (Exception e){
            System.out.println(e);
            return null;
        }
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
                    optionService.addOptions(qiestionId, questionForm.getAddFormOptionList());
                }
                else {
                    return false;
                }
            }
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public boolean deleteQuestions(Long testId){
        try {
            List<Question> questions = questionRepository.findByTestId(testId);
            if (!questions.isEmpty()){
                for (Question question: questions){
                    optionService.deleteAnswers(question.getId());
                    optionService.deleteOption(question.getType().getId(),question.getId());
                    questionRepository.delete(question);
                }
            }
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public double addAnswerQuestion (Long resultTestId, AddFormAnswerQuestion answerQuestion){
        double points = 0;
        try {
            Question question = questionRepository.findById(answerQuestion.getQuestionId()).get();
            if (question.getType().getId() == 3 && answerQuestion.getAnswerOptions().size() == optionService.getOptionsByQuestionId(question.getType().getId() ,question.getId()).size()){
                points = 0;
            }
            else{
                for(AddFormAnswerOption answerOption: answerQuestion.getAnswerOptions()){
                    points += optionService.addAnswerOption(resultTestId, answerQuestion.getQuestionId(), answerOption);
                }
            }
            return points;
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public double getQuestionPoints (AddFormAnswerQuestion answerQuestion){
        //Не требуется
        return 0.0;
    }
}
