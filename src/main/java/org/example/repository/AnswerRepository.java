package org.example.repository;

import org.example.models.Admin;
import org.example.models.Answer;
import org.example.models.ResultTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByResulTestId(Long resultTestId);
    List<Answer> findByQuestionId(Long questionId);
}
