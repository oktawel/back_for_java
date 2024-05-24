package org.example.services;

import org.example.models.FreeOption;
import org.example.models.OneOption;

import java.util.List;
import java.util.Optional;

public interface OneOptionService {
    boolean deleteOption(Long optionId);
    List<OneOption> findByQuestionId(Long questionId);
    boolean saveOption(OneOption option);
    Optional<OneOption> findById(Long id);
}
