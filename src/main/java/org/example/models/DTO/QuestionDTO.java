package org.example.models.DTO;

import org.example.models.TypeQuestion;

import java.util.List;
import java.util.Set;

public class QuestionDTO {
    private Long id;
    private String text;
    private Long testId;
    private Long typeQuestionID;
    private int cost;
    private List<OptionDTO> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getTypeQuestionID() {
        return typeQuestionID;
    }

    public void setTypeQuestionID(Long typeQuestionID) {
        this.typeQuestionID = typeQuestionID;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }
}
