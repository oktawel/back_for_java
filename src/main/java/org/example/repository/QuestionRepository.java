package org.example.repository;

import org.example.models.Question;
import org.example.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Long>{
    List<Question> findByTestId(Long testId);
    default Long saveAndReturnId(Question question) {
        Question savedQuestion = save(question);
        return savedQuestion.getId();
    }
}
