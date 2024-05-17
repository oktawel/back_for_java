package org.example.repository;

import org.example.models.Grooup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Grooup, Long> {

}
