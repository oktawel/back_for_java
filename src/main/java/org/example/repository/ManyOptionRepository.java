package org.example.repository;

import org.example.models.Admin;
import org.example.models.FreeOption;
import org.example.models.ManyOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManyOptionRepository extends JpaRepository<ManyOption, Long> {
    List<ManyOption> findByQuestionId(Long questionId);
}
