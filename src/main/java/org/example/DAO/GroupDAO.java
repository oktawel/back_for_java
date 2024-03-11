package org.example.DAO;

import org.example.models.Grooup;

import java.util.List;

public interface GroupDAO {
    Grooup getById(Long id);
    Grooup getByName(String name);
    List<Grooup> getAll();
    boolean add(Grooup group);
    boolean update(Grooup group);
    boolean delete(Long id);
}
