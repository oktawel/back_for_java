package org.example.repository;

import org.example.models.Lecturer;
import org.example.models.ResultTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    List<Lecturer> findByName(String name);
    List<Lecturer> findBySurname(String surname);
}
