package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.example.models.*;
import org.example.DAO.*;
import org.example.models.forAdmin.*;
import org.example.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "org.example")
public class App
{

//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { WebConfig.class }; // Класс конфигурации CORS
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[] { WebConfig.class }; // Класс конфигурации CORS
//    }

    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    public static void main( String[] args ) throws ParseException {
//          SpringApplication.run(Application.class, args);

//        ApplicationContext	context	= new ClassPathXmlApplicationContext("beans.xml");
//        AdminService r = context.getBean("AdminService", AdminService.class);
//
//        //AdminService r = new AdminServiceImpl();
//
//
//        AddFormLecturer form = new AddFormLecturer();
//        form.setName("Никита");
//        form.setSurname("Сорокин");
//        r.create_updateLecturer(form);
//
//        AddFormGrooup form2 = new AddFormGrooup();
//        form2.setName("ИСТ-122");
//        r.create_updateGrooup(form2);
//
//        AddFormStudent form3 = new AddFormStudent();
//        form3.setName("Иван");
//        form3.setSurname("Большев");
//        form3.setGroupId(1L);
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        date = dateFormat.parse("13/05/2003");
//        form3.setBirthDate(date);
//
//        r.create_updateStudent(form3);
//
//
//        List<Student> students = r.getAllStudents();
//        for (Student student: students){
//            System.out.println(student.getId());
//            System.out.println(student.getName());
//            System.out.println(student.getSurname());
//            System.out.println(student.getBirthDate());
//            System.out.println(student.getGroup().getName());
//            System.out.println(student.getUser().getLogin());
//            System.out.println(student.getUser().getPassword());
//            System.out.println(student.getUser().getRole().getName());
//            System.out.println("--------------------------------------------");
//        }

    }
}

