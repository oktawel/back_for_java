package org.example.repository;

import org.example.models.OneOption;
import org.example.models.OptionTF_Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionTF_QuestionRepository extends JpaRepository<OptionTF_Question, Long> {

}
