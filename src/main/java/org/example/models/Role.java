package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = true)
	private String name;

	// Геттер для поля id
	public Long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(Long id) {
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