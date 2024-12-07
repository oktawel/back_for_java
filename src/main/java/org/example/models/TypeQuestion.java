package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;
@Entity
@Table(name = "TypeQuestion")
public class TypeQuestion{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "text", nullable = false)
	private String text;

	// Геттер для поля id
	public Long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(Long id) {
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
}