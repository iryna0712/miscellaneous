package com.sqltest.eagerlazyloading;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {

    public static void main(String[] args) {

        try( SessionFactory factory = new Configuration()
                .configure("hibernate_onetomany_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory() ) {

            ///////////////////////////////////// Session for creating rows

            Instructor tempInstructor = new Instructor("Irinka", "Queen", "queen@example.com");
            InstructorDetail detail = new InstructorDetail("http://youtube.com/hahah", "sleeping");
            tempInstructor.setInstructorDetail(detail);

            Instructor tempInstructor2 = new Instructor("Irinka2", "Queen2", "queen@example.com");
            InstructorDetail detail2 = new InstructorDetail("http://youtube.com/hahah2", "sleeping");
            tempInstructor2.setInstructorDetail(detail2);

            Session session = factory.getCurrentSession();

            try {
                session.beginTransaction();

                session.save(tempInstructor);
                session.save(tempInstructor2);

                session.getTransaction().commit();

                session = factory.getCurrentSession();
                session.beginTransaction();

                int theId = 1;
                Instructor instructor = session.get(Instructor.class, theId);

//                Course course1 = new Course("algebra");
//                Course course2 = new Course("gaming");
//                Course course3 = new Course("cooking");
//
//                instructor.addCourse(course1);
//                instructor.addCourse(course2);
//                instructor.addCourse(course3);
//
//                session.save(course1);
//                session.save(course2);
//                session.save(course3);

                session.getTransaction().commit();

                session = factory.getCurrentSession();
                session.beginTransaction();

                theId = 1;
                Instructor tempInstructorForPrint = session.get(Instructor.class, theId);

                System.out.println("Instructor : " + tempInstructorForPrint);
                System.out.println("Instructor : " + tempInstructorForPrint.getFirstName());

                session.getTransaction().commit();

                System.out.println("Instructor : " + tempInstructorForPrint);
                System.out.println("Instructor : " + tempInstructorForPrint.getFirstName());

                session = factory.getCurrentSession();
                session.beginTransaction();

                Query<Instructor> query = session.createQuery(
                        "select i from Instructor i " +
                        "JOIN FETCH i.courses " +
                        "where i.id=:theInstructorId", Instructor.class);
                query.setParameter("theInstructorId", theId);
                Instructor instructorResult = query.getSingleResult();

                session.getTransaction().commit();
                session.close();


            } finally {
                //do we need to close every time?
                session.close();
            }
            //


        }

    }
}
