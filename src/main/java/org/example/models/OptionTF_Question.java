package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "OptionTF_Question")
public class OptionTF_Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "optionTF_id", nullable = false)
	private TFOption tfOption;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TFOption getTfOption() {
		return tfOption;
	}

	public void setTfOption(TFOption tfOption) {
		this.tfOption = tfOption;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}