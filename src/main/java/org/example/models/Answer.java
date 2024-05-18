package org.example.models;

import jakarta.persistence.*;
import org.example.models.*;

@Entity
@Table(name = "Answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question ;
	@Column(name = "free", nullable = true)
	private String free;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "one_id", nullable = true)
	private OneOption oneOption;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "many_id", nullable = true)
	private ManyOption manyOption;
	@Column(name = "correct", nullable = false)
	private boolean correct ;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "result_test_id", nullable = true)
	private ResultTest resultTest ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public OneOption getOneOption() {
		return oneOption;
	}

	public void setOneOption(OneOption oneOption) {
		this.oneOption = oneOption;
	}

	public ManyOption getManyOption() {
		return manyOption;
	}

	public void setManyOption(ManyOption manyOption) {
		this.manyOption = manyOption;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public ResultTest getResultTest() {
		return resultTest;
	}

	public void setResultTest(ResultTest resultTest) {
		this.resultTest = resultTest;
	}
}