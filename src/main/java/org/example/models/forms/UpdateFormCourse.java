package org.example.models.forms;

import java.util.Set;

public class UpdateFormCourse {
    private Long id;
    private String name;
    private String description;
    private Long lecturerId;
    private Set<Long> groupsId;

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

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Set<Long> getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(Set<Long> groupsId) {
        this.groupsId = groupsId;
    }
}
