package org.example.services;

import org.example.models.FreeOption;
import org.example.models.OptionTF_Question;
import org.example.models.TFOption;

import java.util.List;

public interface TFOptionService {
    boolean deleteOption(Long optionId);
    TFOption getTFOptionByTextAndCorrect(String text, boolean correct);
    List<OptionTF_Question> getAllConnects();
    boolean saveOption(OptionTF_Question option);
}
