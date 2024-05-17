package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.models.Subject;
@Repository
public interface CourseRepository extends JpaRepository <Subject, Long>{

}
