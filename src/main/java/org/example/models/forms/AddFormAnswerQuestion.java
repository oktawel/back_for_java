package org.example.models.forms;

import java.util.List;
import java.util.Set;

public class AddFormAnswerQuestion {
    private Long questionId;


    private List<AddFormAnswerOption> answerOptions;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<AddFormAnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<AddFormAnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
