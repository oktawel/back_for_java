package org.example.models;

import org.example.models.*;

public class ResultTest{

	private Long id;
	private Test test;
	private Users user;
	private int mark;

	// Геттер для поля id
	public Long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(Long id) {
		this.id = id;
	}

	// Геттер для поля test
	public Test getTest() {
		return test;
	}

	// Сеттер для поля test
	public void setTest(Test test) {
		this.test = test;
	}

	// Геттер для поля user
	public Users getUser() {
		return user;
	}

	// Сеттер для поля user
	public void setUser(Users user) {
		this.user = user;
	}

	// Геттер для поля mark
	public int getMark() {
		return mark;
	}

	// Сеттер для поля mark
	public void setMark(int mark) {
		this.mark = mark;
	}
}