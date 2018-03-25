package com.sqltest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        javax.xml.bind.JAXBException exception;

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {


            Session session = factory.getCurrentSession();

            Student st = new Student("John", "Doe", "johndoe@example.com");

            session.beginTransaction();
            session.save(st);
            session.getTransaction().commit();

 //       }
        } finally {
            factory.close();
        }



    }
}

