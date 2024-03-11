package org.example.DAO;

import org.example.models.Student;

import java.util.List;

public interface StudentDAO {
    Student getById(Long id);
    Student getByName(String name);
    Student getBySurname(String surname);
    List<Student> getAll();
    boolean add(Student student);
    boolean update(Student student);
    boolean delete(Long id);
}
