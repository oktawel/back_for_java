package org.example.repository;

import org.example.models.Admin;
import org.example.models.Question;
import org.example.models.ResultTest;
import org.example.models.TFOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultTestRepository extends JpaRepository <ResultTest, Long>{
    Optional<ResultTest> findByStudentIdAndTestId(Long studentId, Long testId);
    List<ResultTest> findByStudentId(Long studentId);
    List<ResultTest> findByTestId(Long testId);
    default Long saveAndReturnId(ResultTest resultTest) {
        ResultTest savedResultTest = save(resultTest);
        return savedResultTest.getId();
    }

}
