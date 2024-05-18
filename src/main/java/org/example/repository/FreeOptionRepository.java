package org.example.repository;

import org.example.models.Admin;
import org.example.models.FreeOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeOptionRepository extends JpaRepository<FreeOption, Long> {

}
