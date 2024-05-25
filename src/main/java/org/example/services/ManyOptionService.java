package org.example.services;

import org.example.models.FreeOption;
import org.example.models.ManyOption;
import org.example.models.OneOption;

import java.util.List;
import java.util.Optional;

public interface ManyOptionService {
    boolean deleteOption(Long optionId);
    List<ManyOption> findByQuestionId(Long questionId);
    boolean saveOption(ManyOption option);
    Optional<ManyOption> findById(Long id);
    List<ManyOption> getOptionsByQuestionAndCorrect(Long questionId, boolean correct);
}
