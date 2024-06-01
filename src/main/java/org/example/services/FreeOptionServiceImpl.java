package org.example.services;

import org.example.models.FreeOption;
import org.example.models.Test;
import org.example.repository.FreeOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class FreeOptionServiceImpl implements FreeOptionService{
    @Autowired
    private FreeOptionRepository freeOptionRepository;
    @Override
    public boolean deleteOption(Long optionId){
        try {
            Optional<FreeOption> optionOptional = freeOptionRepository.findById(optionId);
            if (optionOptional.isPresent()) {
                FreeOption option = optionOptional.get();
                freeOptionRepository.delete(option);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Optional<FreeOption> findByQuestionId(Long questionId){
        return freeOptionRepository.findByQuestionId(questionId);
    }
    @Override
    public boolean saveOption(FreeOption option){
        try {
            freeOptionRepository.save(option);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

}
