package org.example.repository;

import org.example.models.Admin;
import org.example.models.FreeOption;
import org.example.models.ManyOption;
import org.example.models.ResultTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManyOptionRepository extends JpaRepository<ManyOption, Long> {
    List<ManyOption> findByQuestionId(Long questionId);
    List<ManyOption> findByQuestionIdAndCorrect(Long questionId, boolean correct);
}
