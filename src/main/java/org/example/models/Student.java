package org.example.models;

import java.util.Date;

import jakarta.persistence.*;
import org.example.models.*;
@Entity
@Table(name = "Student")
public class Student{
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
	@Column(name = "birthDate", nullable = false)
	private Date birthDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groop_id", nullable = false)
	private Grooup group;

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

	// Геттер для поля birthDate
	public Date getBirthDate() {
		return birthDate;
	}

	// Сеттер для поля birthDate
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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