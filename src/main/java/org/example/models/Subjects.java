package org.example.models;

import org.example.models.*;

public class Subjects{

	private long id;
	private Subject subject;
	private Grooup group;

	// Геттер для поля id
	public long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(long id) {
		this.id = id;
	}

	// Геттер для поля subject
	public Subject getSubject() {
		return subject;
	}

	// Сеттер для поля subject
	public void setSubject(Subject subject) {
		this.subject = subject;
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