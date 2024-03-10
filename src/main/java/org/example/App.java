package org.example;

import org.example.models.*;
import org.example.DAO.*;
import org.example.models.forAdmin.*;
import org.example.services.*;

import java.util.List;

public class App 
{


    public static void main( String[] args )
    {
        UserDAO userDAO = new UserDAOImpl();
        RoleDAO roleDAO = new RoleDAOImpl();

        AdminServiceImpl r = new AdminServiceImpl();

        AddFormLecturer form = new AddFormLecturer();
        form.setName("Иван");
        form.setSurname("Большев");


        r.addNewLecturer(form);





//        /**Users us = new Users();
//        us.setLogin("stu-ist121");
//        us.setPassword("123321");
//        Role role = roleDAO.getById(1L);
//        userDAO.add(us, role);**/
//
//
//        Users us2 = userDAO.getById(2L);
//        us2.setPassword("525");
//        userDAO.update(us2);
//
//        userDAO.delete(4L);
//
//        List<Users> users = userDAO.getAll();
//        for (Users user: users)
//        {
//            System.out.println(user.getLogin());
//        }
    }
}

