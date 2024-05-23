package org.example.models.forms;

import jakarta.persistence.*;
import org.example.models.Test;
import org.example.models.TypeQuestion;

import java.util.List;

public class AddFormQuestion {

    private Long id;
    private String text;
    private Long testId;
    private Long typeId;
    private int cost;

    private List<AddFormOption> addFormOptionList;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<AddFormOption> getAddFormOptionList() {
        return addFormOptionList;
    }

    public void setAddFormOptionList(List<AddFormOption> addFormOptionList) {
        this.addFormOptionList = addFormOptionList;
    }
}
