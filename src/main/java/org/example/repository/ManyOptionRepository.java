package org.example.repository;

import org.example.models.Admin;
import org.example.models.ManyOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManyOptionRepository extends JpaRepository<ManyOption, Long> {

}
