package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;
@Entity
@Table(name = "Admin")
public class Question{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "text", nullable = false)
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id", nullable = false)
	private Test test;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private TypeQuestion type;
	@Column(name = "cost", nullable = false)
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