package org.example.repository;

import org.example.models.Admin;
import org.example.models.Question;
import org.example.models.ResultTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultTestRepository extends JpaRepository <ResultTest, Long>{

    default Long saveAndReturnId(ResultTest resultTest) {
        ResultTest savedResultTest = save(resultTest);
        return savedResultTest.getId();
    }

}
