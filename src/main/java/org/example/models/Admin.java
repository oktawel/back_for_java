package org.example.models;

import org.example.models.*;
import jakarta.persistence.*;
@Entity
@Table(name = "Admin")
public class Admin{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_id", nullable = false)
	private Users user;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "surname", nullable = false)
	private String surname;

	// Геттер для поля id
	public Long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(Long id) {
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
}