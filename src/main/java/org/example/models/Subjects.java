package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;
@Entity
@Table(name = "Subjects")
public class Subjects{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groop_id", nullable = false)
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