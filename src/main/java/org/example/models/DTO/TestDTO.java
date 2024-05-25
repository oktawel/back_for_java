package org.example.models.DTO;

import jakarta.persistence.*;
import org.example.models.Subject;

public class TestDTO {
    private Long id;
    private String name;
    private String description;
    private boolean open;
    private Long SubjectId;
    private Double mark;

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

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
