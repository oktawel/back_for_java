package org.example.models.DTO;

import org.example.models.Grooup;

import java.util.Set;

public class GroupDTOForCourse {
    private Long id;

    private String name;

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
}
