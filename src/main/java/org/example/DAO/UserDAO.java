package org.example.DAO;

import org.example.models.Role;
import org.example.models.Users;

import java.util.List;

public interface UserDAO {
    Users getById(Long id);
    List<Users> getAll();
    boolean add(Users user);
    boolean update(Users user);
    boolean delete(Long id);
}
