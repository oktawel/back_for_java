package org.example.repository;

import org.example.models.Admin;
import org.example.models.OneOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneOptionRepository extends JpaRepository<OneOption, Long> {

}
