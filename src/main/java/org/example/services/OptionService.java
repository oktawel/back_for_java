package org.example.services;


import org.example.models.DTO.OptionDTO;
import org.example.models.Question;
import org.example.models.forms.AddFormAnswerOption;
import org.example.models.forms.AddFormOption;
import org.example.models.forms.AddFormQuestion;

import java.util.List;

public interface OptionService {
    List<OptionDTO> getOptionsByQuestionId(Long typeQuestionId, Long questiontId);

    boolean addOptions(Long questionId, List<AddFormOption> options);
    boolean deleteOption(Long typeId, Long questiontId);
    boolean deleteAnswersByResultTestId(Long resultTestId);
    boolean deleteAnswers(Long questionId);
    double addAnswerOption(Long resultTestId, Long questionId, AddFormAnswerOption formOption);
}
