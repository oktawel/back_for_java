package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;
@Entity
@Table(name = "Groop")
public class Grooup{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", nullable = false)
	private String name;

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
}