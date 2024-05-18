package org.example.repository;

import org.example.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeQuestionRepository extends JpaRepository <TestRepository, Long>{

}
