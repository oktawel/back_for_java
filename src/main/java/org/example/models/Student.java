package org.example.models;

import java.util.Date;
import org.example.models.*;

public class Student{

	private long id;
	private Users user;
	private String name;
	private String surname;
	private Date birthDate;
	private Grooup group;

	// Геттер для поля id
	public long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(long id) {
		this.id = id;
	}

	// Геттер для поля user
	public Users getUser() {
		return user;
	}

	// Сеттер для поля user
	public void setUser(Users user) {
		this.user = user;
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

	// Геттер для поля group
	public Grooup getGroup() {
		return group;
	}

	// Сеттер для поля group
	public void setGroup(Grooup group) {
		this.group = group;
	}

}