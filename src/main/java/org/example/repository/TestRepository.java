package org.example.repository;

import org.example.models.Admin;
import org.example.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository <Test, Long>{
    List<Test> findBySubjectId(Long subjectId);
}
