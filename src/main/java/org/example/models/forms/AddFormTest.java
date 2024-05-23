package org.example.models.forms;

import jakarta.persistence.*;
import org.example.models.Subject;
import java.util.List;

public class AddFormTest {

    private Long id;

    private String name;

    private String description;

    private boolean open;

    private Long subjectId;

    private List<AddFormQuestion> addFormQuestionList;

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
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public List<AddFormQuestion> getAddFormQuestionList() {
        return addFormQuestionList;
    }

    public void setAddFormQuestionList(List<AddFormQuestion> addFormQuestionList) {
        this.addFormQuestionList = addFormQuestionList;
    }
}
