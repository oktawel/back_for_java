package org.example.models.DTO;

import jakarta.persistence.*;
import org.example.models.Grooup;
import org.example.models.Lecturer;

import java.util.Set;

public class CourseDTO {
    private Long id;

    private String name;

    private String description;

    private String lecturerName;
    private String lecturerSurname;

    private Set<GroupDTOForCourse> groups;

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

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getLecturerSurname() {
        return lecturerSurname;
    }

    public void setLecturerSurname(String lecturerSurname) {
        this.lecturerSurname = lecturerSurname;
    }

    public Set<GroupDTOForCourse> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupDTOForCourse> groups) {
        this.groups = groups;
    }
}
