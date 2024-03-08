package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.models.*;

public class App 
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Users user = session.get(Users.class, 1L);
            System.out.println(user.getLogin());
            System.out.println(user.getRole().getName());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println( "Hello World!" );
    }
}

