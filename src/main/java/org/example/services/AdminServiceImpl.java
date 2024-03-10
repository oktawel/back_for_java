package org.example.services;

import org.example.DAO.*;
import org.example.models.Role;
import org.example.models.Users;
import org.example.models.Lecturer;
import org.example.models.forAdmin.AddFormLecturer;

import java.util.List;
import java.util.Random;

public class AdminServiceImpl implements AdminService{

    UserDAO userDAO = new UserDAOImpl();
    RoleDAO roleDAO = new RoleDAOImpl();
    LecturerDAO lecturerDAO = new LecturerDAOImpl();

    @Override
    public boolean addNewLecturer(AddFormLecturer form){
        boolean result = false;

        String login = form.getLogin();
        String password = form.getPassword();
        String name = form.getName();
        String surname = form.getSurname();
        if (login == null){login = generateLogin(); }
        if (password == null){password = generateLogin(); }

        Role role = roleDAO.getByName("Lector");

        Users user = new Users();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        if (userDAO.add(user)){
            Lecturer lecturer = new Lecturer();
            lecturer.setName(name);
            lecturer.setSurname(surname);
            lecturer.setUser(user);
            if(lecturerDAO.add(lecturer)){
                result = true;
            }
        }
        return result;
    }


    private String generatePassword (){
        int lenght = 7;
        String characters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lenght; i++ ){
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    private String generateLogin (){
        int lenght = 8;
        String characters = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        StringBuilder login = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lenght; i++ ){
            int index = random.nextInt(characters.length());
            login.append(characters.charAt(index));
        }
        return login.toString();
    }
}
