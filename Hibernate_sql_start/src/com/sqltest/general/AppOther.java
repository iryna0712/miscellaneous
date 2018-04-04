package com.sqltest.general;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class AppOther {

    //Logger

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate_general.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try {

            Session session = factory.getCurrentSession();

            session.beginTransaction();
            List<Student> students;
            students = session.createQuery("from Student").getResultList();
            System.out.println("students " + students + "\n");

            students = session.createQuery("from Student s where s.lastName='Doe4' or s.email like '%gmail.com'").getResultList();
            System.out.println("students " + students + "\n");

            students = session.createQuery("from Student s where s.email like '%yahoo.com'").getResultList();
            System.out.println("students " + students + "\n");

            Student myStudent = session.get(Student.class, 1);
            myStudent.setEmail("changed_email.@example.com");

            //actually apply
            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            Query query2 = session.createQuery("update Student s set s.email='changed_2@example.com' where s.firstName='John2'");
            query2.executeUpdate();

            session.getTransaction().commit();

            ///////////////// DELETE
            session = factory.getCurrentSession();
            session.beginTransaction();

            Query query3 = session.createQuery("delete Student where id=3");
            query3.executeUpdate();

            Student studentToDelete = session.get(Student.class, 4);
            session.delete(studentToDelete);

            session.getTransaction().commit();

 //       }
        } finally {
            factory.close();
        }



    }
}

