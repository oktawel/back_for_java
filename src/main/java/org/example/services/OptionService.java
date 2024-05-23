package org.example.services;


import org.example.models.DTO.OptionDTO;
import org.example.models.Question;

import java.util.List;

public interface OptionService {
    List<OptionDTO> getOptionsByQuestionId(Long typeQuestionId, Long questiontId);

}
