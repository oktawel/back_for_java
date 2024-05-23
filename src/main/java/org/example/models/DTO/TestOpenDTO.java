package org.example.models.DTO;

import org.example.models.Question;

import java.util.List;
import java.util.Set;

public class TestOpenDTO {
    private Long id;
    private String name;
    private String description;
    private boolean open;
    private Long SubjectId;
    private List<QuestionDTO> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Long getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(Long subjectId) {
        SubjectId = subjectId;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
