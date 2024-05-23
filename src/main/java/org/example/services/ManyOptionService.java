package org.example.services;

import org.example.models.FreeOption;
import org.example.models.ManyOption;

import java.util.List;

public interface ManyOptionService {
    boolean deleteOption(Long optionId);
    List<ManyOption> findByQuestionId(Long questionId);

    boolean saveOption(ManyOption option);
}
