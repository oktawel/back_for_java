package org.example.repository;

import org.example.models.Admin;
import org.example.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository <Test, Long>{

}
