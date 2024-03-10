package org.example.DAO;

import org.example.models.Role;

import java.util.List;

public interface RoleDAO {

        Role getById(Long id);
        Role getByName(String name);
        List<Role> getAll();
        boolean add(Role role);
        boolean update(Role role);
        boolean delete(Long id);
}
