package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;

import java.util.Set;
@Entity
@Table(name = "Subject")
public class Subject{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", nullable = false)
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lector_id", nullable = false)
	private Lecturer lecturer;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "group_course",
			joinColumns = @JoinColumn(name = "subject_id"),
			inverseJoinColumns = @JoinColumn(name = "groop_id")
	)
	private Set<Grooup> groups;

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

	// Геттер для поля description
	public String getDescription() {
		return description;
	}

	// Сеттер для поля description
	public void setDescription(String description) {
		this.description = description;
	}

	// Геттер для поля lecturer
	public Lecturer getLecturer() {
		return lecturer;
	}

	// Сеттер для поля lecturer
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Set<Grooup> getGroups() {
		return groups;
	}

	public void setGroups(Set<Grooup> groups) {
		this.groups = groups;
	}
}