package org.example.repository;

import org.example.models.OneOption;
import org.example.models.TFOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TFOptionRepository extends JpaRepository<TFOption, Long> {

}
