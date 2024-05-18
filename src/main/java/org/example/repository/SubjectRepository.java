package org.example.repository;

import org.example.models.Admin;
import org.example.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository <Subject, Long>{

}
