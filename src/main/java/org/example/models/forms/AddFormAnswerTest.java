package org.example.models.forms;

import java.util.List;
import java.util.Set;

public class AddFormAnswerTest {

    private Long testId;

    private Long studentId;

    private List<AddFormAnswerQuestion> answerQuestions;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long tetId) {
        this.testId = tetId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<AddFormAnswerQuestion> getAnswerQuestions() {
        return answerQuestions;
    }

    public void setAnswerQuestions(List<AddFormAnswerQuestion> answerQuestions) {
        this.answerQuestions = answerQuestions;
    }
}
