package org.example.DAO;

import org.example.models.Users;
import org.example.models.Lecturer;

import java.util.List;

public interface LecturerDAO {
    Lecturer getById(Long id);
    List<Lecturer> getAll();
    boolean add(Lecturer lecturer);
    boolean update(Lecturer lecturer);
    boolean delete(Long id);
}
