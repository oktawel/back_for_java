package org.example.services;

import org.example.models.ManyOption;
import org.example.models.OneOption;
import org.example.models.OptionTF_Question;
import org.example.models.TFOption;
import org.example.repository.ManyOptionRepository;
import org.example.repository.OptionTF_QuestionRepository;
import org.example.repository.TFOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TFOptionServiceImpl implements TFOptionService{
    @Autowired
    private OptionTF_QuestionRepository optionTFQuestionRepository;
    @Autowired
    private TFOptionRepository tfOptionRepository;
    @Override
    public boolean deleteOption(Long optionId){
        try {
            Optional<OptionTF_Question> optionOptional = optionTFQuestionRepository.findById(optionId);
            if (optionOptional.isPresent()) {
                OptionTF_Question option = optionOptional.get();
                optionTFQuestionRepository.delete(option);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<OptionTF_Question> getAllConnects(){
        return optionTFQuestionRepository.findAll();
    }
    @Override
    public TFOption getTFOptionByTextAndCorrect(String text, boolean correct){
        return tfOptionRepository.findByTextAndCorrect(text, correct);
    }
    @Override
    public boolean saveOption(OptionTF_Question option){
        try {
            optionTFQuestionRepository.save(option);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public Optional<TFOption> findById(Long id){
        return tfOptionRepository.findById(id);
    }
}
