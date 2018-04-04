package com.sqltest.general;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class AppCreatingReading {

    public static void main(String[] args) {

        try( SessionFactory factory = new Configuration()
                .configure("hibernate_general.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory() ) {

            ///////////////////////////////////// Session for creating rows
            Session session = factory.getCurrentSession();

            Student st1 = new Student("John1", "Doe1", "johndoe1@example.com");
            Student st2 = new Student("John2", "Doe2", "johndoe2@example.com");
            Student st3 = new Student("John3", "Doe3", "johndoe3@gmail.com");
            Student st4 = new Student("John4", "Doe4", "johndoe4@gmail.com");

            Date birth = null;
            try {
                birth = DateUtils.parseDate("31/12/1988");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Student st5 = new Student("John5", "Doe5", "johndoe5@gmail.com", birth);

            session.beginTransaction();
            session.save(st1);
            session.save(st2);
            session.save(st3);
            session.save(st4);
            session.save(st5);
            session.getTransaction().commit();

            ///////////////////////////////////// Session for reading data
            session = factory.getCurrentSession();
            session.beginTransaction();

            Student myStudent = session.get(Student.class, st2.getId());
            System.out.println("myStudent " + myStudent);

            Query query = session.createQuery("from Student s where s.lastName='Doe1' OR s.firstName='John3'");
            //Query query = session.createQuery("from com.sqltest.general.Student");
            List<Student> students = query.getResultList();

            System.out.println("students " + students);

            session.getTransaction().commit();

 //       }
        }

    }
}

