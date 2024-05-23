package org.example.services;

import org.example.models.DTO.QuestionDTO;
import org.example.models.DTO.TestDTO;
import org.example.models.Question;
import org.example.models.Test;
import org.example.models.forms.AddFormTest;
import org.example.repository.CourseRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.TestRepository;
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
    private QuestionRepository questionRepository;
    @Autowired
    private OptionService optionService;
    @Override
    public List<QuestionDTO> getQuestionsByTestId(Long testId) {
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
}
