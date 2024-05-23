package org.example.services;


import org.example.models.*;
import org.example.models.DTO.OptionDTO;
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
    private FreeOptionRepository freeOptionRepository;
    @Autowired
    private ManyOptionRepository manyOptionRepository;
    @Autowired
    private OneOptionRepository oneOptionRepository;
    @Autowired
    private TFOptionRepository tfOptionRepository;
    @Autowired
    private OptionTF_QuestionRepository optionTFQuestionRepository;
    public List<OptionDTO> getOptionsByQuestionId(Long typeQuestionId, Long questiontId) {
        List<OptionDTO> optionDTOS = new ArrayList<>();


        switch (typeQuestionId.intValue()){
//            case (1):{
//                Optional<FreeOption> option = freeOptionRepository.findByQuestionId(questiontId);
//                if (!option.isPresent()){
//                    return null;
//                }
//                FreeOption freeOption = option.get();
//                OptionDTO optionDTO = new OptionDTO();
//                optionDTO.setId(freeOption.getId());
//                optionDTO.setQuestionId(freeOption.getQuestion().getId());
//                optionDTO.setText(freeOption.getText());
//                optionDTO.setCorrect(freeOption.ge);
//                break;
//            }
            case (2):{
                List<OneOption> options = oneOptionRepository.findByQuestionId(questiontId);
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
                List<ManyOption> options = manyOptionRepository.findByQuestionId(questiontId);
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
                List<OptionTF_Question> connects = optionTFQuestionRepository.findAll();
                if (connects.isEmpty()) {
                    return null;
                }
                for (OptionTF_Question connect : connects) {
                    OptionDTO optionDTO = new OptionDTO();
                    optionDTO.setId(connect.getId());
                    optionDTO.setQuestionId(connect.getQuestion().getId());
                    optionDTO.setText(connect.getTfOption().getText());
                    optionDTO.setCorrect(connect.getTfOption().isCorrect());
                    optionDTOS.add(optionDTO);
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
                            freeOptionRepository.save(freeOption);
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
                            oneOptionRepository.save(oneOption);
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
                            manyOptionRepository.save(manyOption);
                            break;
                        }
                        case (4): {
                            System.out.println("4");
                            OptionTF_Question optionTFQuestion = new OptionTF_Question();
                            optionTFQuestion.setQuestion(question);
                            optionTFQuestion.setTfOption(tfOptionRepository.findByTextAndCorrect(option.getText(), option.isCorrect()));
                            if (option.getId() != null){
                                optionTFQuestion.setId(option.getId());
                            }
                            optionTFQuestionRepository.save(optionTFQuestion);
                            break;
                        }
                    }
                }
                else {
                    return false;
                }
            }
            System.out.println("Option add");
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
