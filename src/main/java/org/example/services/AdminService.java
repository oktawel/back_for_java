package org.example.services;

import org.example.DAO.RoleDAO;
import org.example.DAO.RoleDAOImpl;
import org.example.DAO.UserDAO;
import org.example.DAO.UserDAOImpl;
import org.example.models.Lecturer;
import org.example.models.Users;
import org.example.models.forAdmin.*;

public interface AdminService {

    boolean addNewLecturer(AddFormLecturer form);
    //boolean addNewStudent(AddFormStudent form);
    //boolean addNewGrooup(AddFormGrooup form);
}
