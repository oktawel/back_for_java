package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;
@Entity
@Table(name = "Test")
public class Test{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", nullable = false)
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	// Геттер для поля id
	public long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(long id) {
		this.id = id;
	}

	// Геттер для поля name
	public String getName() {
		return name;
	}

	// Сеттер для поля name
	public void setName(String name) {
		this.name = name;
	}

	// Геттер для поля description
	public String getDescription() {
		return description;
	}

	// Сеттер для поля description
	public void setDescription(String description) {
		this.description = description;
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