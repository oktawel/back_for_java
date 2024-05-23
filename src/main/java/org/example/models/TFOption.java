package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "OptionTF")
public class TFOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "text", nullable = false)
	private String text;
	@Column(name = "correct", nullable = false)
	private boolean correct ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}