package org.example.services;

import org.example.models.FreeOption;
import org.example.models.ManyOption;
import org.example.models.OneOption;
import org.example.repository.FreeOptionRepository;
import org.example.repository.OneOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OneOptionServiceImpl implements OneOptionService{
    @Autowired
    private OneOptionRepository oneOptionRepository;
    @Override
    public boolean deleteOption(Long optionId){
        try {
            Optional<OneOption> optionOptional = oneOptionRepository.findById(optionId);
            if (optionOptional.isPresent()) {
                OneOption option = optionOptional.get();
                oneOptionRepository.delete(option);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<OneOption> findByQuestionId(Long questionId){
        return oneOptionRepository.findByQuestionId(questionId);
    }
    @Override
    public boolean saveOption(OneOption option){
        try {
            oneOptionRepository.save(option);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
