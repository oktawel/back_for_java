package org.example.services;


import org.example.models.DTO.GroupDTO;
import org.example.models.DTO.LecturerDTO;
import org.example.models.DTO.QuestionDTO;
import org.example.models.DTO.StudentDTO;
import org.example.models.Question;
import org.example.models.forms.AddFormGrooup;
import org.example.models.forms.AddFormLecturer;
import org.example.models.forms.AddFormQuestion;
import org.example.models.forms.AddFormStudent;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> getQuestionsByTestId(Long testId);
    boolean addQuestions(Long testId, List<AddFormQuestion> questions);

    boolean deleteQuestions(Long testId);
//    boolean deleteQuestion(Long questionId);
}
