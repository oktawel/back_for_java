package org.example.repository;

import org.example.models.Lecturer;
import org.example.models.ResultTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.example.models.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGroupId(Long groop_id);
    List<Student> findByName(String name);
    List<Student> findBySurname(String surname);
}
