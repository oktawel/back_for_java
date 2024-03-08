package org.example.models;

import org.example.models.*;
public class Lecturer{

	private long id;
	private Users user;
	private String name;
	private String surname;
	private Subject subject;

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

	// Геттер для поля subject
	public Subject getSubject() {
		return subject;
	}

	// Сеттер для поля subject
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}