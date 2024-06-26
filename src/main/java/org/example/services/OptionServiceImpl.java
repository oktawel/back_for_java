package org.example.services;


import org.example.models.*;
import org.example.models.DTO.OptionDTO;
import org.example.models.forms.AddFormAnswerOption;
import org.example.models.forms.AddFormOption;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OptionServiceImpl implements OptionService{
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private FreeOptionService freeOptionService;
    @Autowired
    private OneOptionService oneOptionService;
    @Autowired
    private ManyOptionService manyOptionService;
    @Autowired
    private TFOptionService tfOptionService;
    @Autowired
    private ResultTestRepository resultTestRepository;
    @Autowired
    private AnswerRepository answerRepository;


    @Override
    public List<OptionDTO> getOptionsByQuestionId(Long typeQuestionId, Long questiontId) {
        List<OptionDTO> optionDTOS = new ArrayList<>();
        try {
            switch (typeQuestionId.intValue()){
                case (2):{
                    List<OneOption> options = oneOptionService.findByQuestionId(questiontId);
                    if (options.isEmpty()){
                        return null;
                    }
                    for (OneOption oneOption : options){
                        OptionDTO optionDTO = new OptionDTO();
                        optionDTO.setId(oneOption.getId());
                        optionDTO.setQuestionId(oneOption.getQuestion().getId());
                        optionDTO.setText(oneOption.getText());
                        optionDTO.setCorrect(oneOption.isCorrect());
                        optionDTOS.add(optionDTO);
                    }
                    break;
                }
                case (3):{
                    List<ManyOption> options = manyOptionService.findByQuestionId(questiontId);
                    if (options.isEmpty()){
                        return null;
                    }
                    for (ManyOption manyOption : options){
                        OptionDTO optionDTO = new OptionDTO();
                        optionDTO.setId(manyOption.getId());
                        optionDTO.setQuestionId(manyOption.getQuestion().getId());
                        optionDTO.setText(manyOption.getText());
                        optionDTO.setCorrect(manyOption.isCorrect());
                        optionDTOS.add(optionDTO);
                    }
                    break;
                }
                case (4): {
                    List<OptionTF_Question> connects = tfOptionService.getAllConnects();
                    if (connects.isEmpty()) {
                        return null;
                    }
                    for (OptionTF_Question connect : connects) {
                        if (connect.getQuestion().getId().equals(questiontId)) {
                            OptionDTO optionDTO = new OptionDTO();
                            optionDTO.setId(connect.getTfOption().getId());
                            optionDTO.setQuestionId(connect.getQuestion().getId());
                            optionDTO.setText(connect.getTfOption().getText());
                            optionDTO.setCorrect(connect.getTfOption().isCorrect());
                            optionDTOS.add(optionDTO);
                        }
                    }
                    break;
                }
                default:
                {
                    return null;
                }
            }
            return optionDTOS;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }


    }
    @Override
    public boolean addOptions(Long questionId, List<AddFormOption> options){
        try {
            for (AddFormOption option: options){
                Optional<Question> optionalQuestion = questionRepository.findById(questionId);
                if (optionalQuestion.isPresent()){
                    Question question = optionalQuestion.get();
                    switch (question.getType().getId().intValue()){
                        case (1): {
                            System.out.println("1");
                            FreeOption freeOption = new FreeOption();
                            freeOption.setText(option.getText());
                            freeOption.setQuestion(question);
                            if (option.getId() != null){
                                freeOption.setId(option.getId());
                            }
                            freeOptionService.saveOption(freeOption);
                            break;
                        }
                        case (2): {
                            System.out.println("2");
                            OneOption oneOption = new OneOption();
                            oneOption.setText(option.getText());
                            oneOption.setQuestion(question);
                            oneOption.setCorrect(option.isCorrect());
                            if (option.getId() != null){
                                oneOption.setId(option.getId());
                            }
                            oneOptionService.saveOption(oneOption);
                            break;
                        }
                        case (3): {
                            System.out.println("3");
                            ManyOption manyOption = new ManyOption();
                            manyOption.setText(option.getText());
                            manyOption.setQuestion(question);
                            manyOption.setCorrect(option.isCorrect());
                            if (option.getId() != null){
                                manyOption.setId(option.getId());
                            }
                            manyOptionService.saveOption(manyOption);
                            break;
                        }
                        case (4): {
                            System.out.println("4");
                            OptionTF_Question optionTFQuestion = new OptionTF_Question();
                            optionTFQuestion.setQuestion(question);
                            optionTFQuestion.setTfOption(tfOptionService.getTFOptionByTextAndCorrect(option.getText(), option.isCorrect()));
                            if (option.getId() != null){
                                optionTFQuestion.setId(option.getId());
                            }
                            tfOptionService.saveOption(optionTFQuestion);
                            break;
                        }
                    }
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
    public boolean deleteOption(Long typeQuestionId, Long questiontId){
        try {
            switch (typeQuestionId.intValue()){
                case (1):{
                    Optional<FreeOption> option = freeOptionService.findByQuestionId(questiontId);
                    freeOptionService.deleteOption(option.get().getId());
                    break;
                }
                case (2):{
                    List<OneOption> options = oneOptionService.findByQuestionId(questiontId);
                    for (OneOption oneOption : options){
                        oneOptionService.deleteOption(oneOption.getId());
                    }
                    break;
                }
                case (3):{
                    List<ManyOption> options = manyOptionService.findByQuestionId(questiontId);
                    for (ManyOption manyOption : options){
                        manyOptionService.deleteOption(manyOption.getId());
                    }
                    break;
                }
                case (4):{
                    List<OptionTF_Question> connects = tfOptionService.getAllConnects();
                    for (OptionTF_Question connect : connects) {
                        if (connect.getQuestion().getId().equals(questiontId)) {
                            tfOptionService.deleteOption(connect.getId());
                        }
                    }
                    break;
                }
                default:{
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
    public boolean deleteAnswers(Long questionId){
        try {
            for (Answer answer: answerRepository.findByQuestionId(questionId)){
                answerRepository.delete(answer);
            }
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteAnswersByResultTestId(Long resultTestId){
        try {
            for (Answer answer: answerRepository.findByResultTestId(resultTestId)){
                answerRepository.delete(answer);
            }
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public double addAnswerOption(Long resultTestId, Long questionId, AddFormAnswerOption formOption){
        Answer answer = new Answer();
        double point = 0;
        try{
            Question question = questionRepository.findById(questionId).get();
            answer.setQuestion(question);
            answer.setResultTest(resultTestRepository.findById(resultTestId).get());
            switch (question.getType().getId().intValue()){
                case (1):{
                    String textAnswer = formOption.getTextAnswer();
                    answer.setFree(textAnswer);
                    if (textAnswer.equals(freeOptionService.findByQuestionId(questionId).get().getText())){
                        answer.setCorrect(true);
                        point += question.getCost();
                    }
                    else {
                        answer.setCorrect(false);
                    }
                    break;
                }
                case (2):{
                    OneOption oneOption = oneOptionService.findById(formOption.getOptionId()).get();
                    answer.setOneOption(oneOption);
                    boolean correct = oneOption.isCorrect();
                    answer.setCorrect(correct);
                    if (correct){
                        point += question.getCost();
                    }
                    break;
                }
                case (3):{
                    ManyOption manyOption = manyOptionService.findById(formOption.getOptionId()).get();
                    answer.setManyOption(manyOption);
                    boolean correct = manyOption.isCorrect();
                    answer.setCorrect(correct);
                    int quantity = manyOptionService.getOptionsByQuestionAndCorrect(questionId, true).size();
                    if (correct){
                        point += (double) question.getCost() /quantity;
                    }
                    break;
                }
                case (4):{
                    TFOption tfOption = tfOptionService.findById(formOption.getOptionId()).get();
                    answer.setTfOption(tfOption);
                    boolean correct = tfOption.isCorrect();
                    answer.setCorrect(correct);
                    if (correct){
                        point += question.getCost();
                    }
                    break;
                }
                default:
                    return 0;
            }
            answerRepository.save(answer);
            return point;
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

}
