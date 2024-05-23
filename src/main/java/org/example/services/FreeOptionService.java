package org.example.services;

import org.example.models.FreeOption;

import java.util.Optional;

public interface FreeOptionService {
    boolean deleteOption(Long optionId);
    Optional<FreeOption> findByQuestionId(Long questionId);
    boolean saveOption(FreeOption option);
}
