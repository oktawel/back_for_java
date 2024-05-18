package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;
@Entity
@Table(name = "ResultTest")
public class ResultTest{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id", nullable = false)
	private Test Test ;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Student Student;
	@Column(name = "mark", nullable = true)
	private Double mark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public org.example.models.Test getTest() {
		return Test;
	}

	public void setTest(org.example.models.Test test) {
		Test = test;
	}

	public org.example.models.Student getStudent() {
		return Student;
	}

	public void setStudent(org.example.models.Student student) {
		Student = student;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}
}