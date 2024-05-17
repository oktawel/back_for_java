package org.example.models.forAdmin;

import org.example.models.Grooup;
import org.example.models.Users;

import java.util.Date;

public class AddFormStudent {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Date birthDate;
    private Long groupId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Геттер для поля login
    public String getLogin() {
        return login;
    }

    // Сеттер для поля login
    public void setLogin(String login) {
        this.login = login;
    }

    // Геттер для поля password
    public String getPassword() {
        return password;
    }

    // Сеттер для поля password
    public void setPassword(String password) {
        this.password = password;
    }

    // Геттер для поля name
    public String getName() {
        return name;
    }

    // Сеттер для поля name
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для поля surname
    public String getSurname() {
        return surname;
    }

    // Сеттер для поля surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Геттер для поля birthDate
    public Date getBirthDate() {
        return birthDate;
    }

    // Сеттер для поля birthDate
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    // Геттер для поля groupId
    public Long getGroupId() {
        return groupId;
    }

    // Сеттер для поля groupId
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
