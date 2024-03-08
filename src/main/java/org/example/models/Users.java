package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "login", nullable = false, unique = true)
	private String login;
	@Column(name = "password", nullable = false)
	private String password;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	// Геттер для поля id
	public long getId() {
		return id;
	}

	// Сеттер для поля id
	public void setId(long id) {
		this.id = id;
	}

	// Геттер для поля login
	public String getLogin() {
		return login;
	}

	// Сеттер для поля login
	public void setLogin(String login) {
		this.login = login;
	}

	// Геттер для поля password
	public String getPassword() {
		return password;
	}

	// Сеттер для поля password
	public void setPassword(String password) {
		this.password = password;
	}

	// Геттер для поля role
	public Role getRole() {
		return role;
	}

	// Сеттер для поля role
	public void setRole(Role role) {
		this.role = role;
	}
}