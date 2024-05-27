package org.example.repository;

import org.example.models.Grooup;
import org.example.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Grooup, Long> {
    List<Grooup> findByName(String name);
}
