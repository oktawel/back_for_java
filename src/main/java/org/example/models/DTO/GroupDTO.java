package org.example.models.DTO;

import java.util.Set;

public class GroupDTO {
    private Long id;

    private String name;

    private Set<CourseDTOForGroup> courses;

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

    public Set<CourseDTOForGroup> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTOForGroup> courses) {
        this.courses = courses;
    }
}
