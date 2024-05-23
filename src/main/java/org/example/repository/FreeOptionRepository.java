package org.example.repository;

import org.example.models.Admin;
import org.example.models.FreeOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreeOptionRepository extends JpaRepository<FreeOption, Long> {
    Optional<FreeOption> findByQuestionId(Long questionId);
}
