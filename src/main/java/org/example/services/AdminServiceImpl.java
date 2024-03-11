package org.example.services;

import org.example.DAO.*;
import org.example.models.*;
import org.example.models.forAdmin.AddFormGrooup;
import org.example.models.forAdmin.AddFormLecturer;
import org.example.models.forAdmin.AddFormStudent;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class AdminServiceImpl implements AdminService{

    UserDAO userDAO = new UserDAOImpl();
    RoleDAO roleDAO = new RoleDAOImpl();
    GroupDAO groupDAO = new GroupDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    LecturerDAO lecturerDAO = new LecturerDAOImpl();

    @Override
    public boolean addNewLecturer(AddFormLecturer form){
        boolean result = false;

        String login = form.getLogin();
        String password = form.getPassword();
        String name = form.getName();
        String surname = form.getSurname();
        if (login == null){login = generateLogin(); }
        if (password == null){password = generatePassword(); }

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

    @Override
    public boolean addNewGrooup(AddFormGrooup form){
        boolean result = false;
        Grooup group = new Grooup();

        group.setName(form.getName());
        if (groupDAO.add(group)){
            result = true;
        }
        return result;
    }

    @Override
    public boolean addNewStudent(AddFormStudent form){
        boolean result = false;

        String login = form.getLogin();
        String password = form.getPassword();
        String name = form.getName();
        String surname = form.getSurname();
        Date birthDate = form.getBirthDate();
        Long groupId = form.getGroupId();

        if (login == null){login = generateLogin(); }
        if (password == null){password = generatePassword(); }

        Role role = roleDAO.getByName("Student");

        Users user = new Users();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        if (userDAO.add(user)){
            Student student = new Student();
            student.setName(name);
            student.setSurname(surname);
            student.setBirthDate(birthDate);
            student.setGroup(groupDAO.getById(groupId));
            student.setUser(user);

            if(studentDAO.add(student)){
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
