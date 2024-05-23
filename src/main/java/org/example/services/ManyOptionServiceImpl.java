package org.example.services;

import org.example.models.FreeOption;
import org.example.models.ManyOption;
import org.example.models.OneOption;
import org.example.repository.ManyOptionRepository;
import org.example.repository.OneOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ManyOptionServiceImpl implements ManyOptionService{
    @Autowired
    private ManyOptionRepository manyOptionRepository;
    @Override
    public boolean deleteOption(Long optionId){
        try {
            Optional<ManyOption> optionOptional = manyOptionRepository.findById(optionId);
            if (optionOptional.isPresent()) {
                ManyOption option = optionOptional.get();
                manyOptionRepository.delete(option);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<ManyOption> findByQuestionId(Long questionId){
        return manyOptionRepository.findByQuestionId(questionId);
    }
    @Override
    public boolean saveOption(ManyOption option){
        try {
            manyOptionRepository.save(option);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
