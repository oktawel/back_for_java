package org.example.models;

import org.example.models.*;

public class Question{

	private long id;
	private String text;
	private Test test;
	private TypeQuestion type;
	private int cost;

	// Геттер для поля id
	public long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(long id) {
		this.id = id;
	}

	// Геттер для поля text
	public String getText() {
		return text;
	}

	// Сеттер для поля text
	public void setText(String text) {
		this.text = text;
	}

	// Геттер для поля test
	public Test getTest() {
		return test;
	}

	// Сеттер для поля test
	public void setTest(Test test) {
		this.test = test;
	}

	// Геттер для поля type
	public TypeQuestion getType() {
		return type;
	}

	// Сеттер для поля type
	public void setType(TypeQuestion type) {
		this.type = type;
	}

	// Геттер для поля cost
	public int getCost() {
		return cost;
	}

	// Сеттер для поля cost
	public void setCost(int cost) {
		this.cost = cost;
	}
}