package com.sqltest.onetomany.uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {

    public static void main(String[] args) {

        try( SessionFactory factory = new Configuration()
                .configure("hibernate_onetomany_with_reviews.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory() ) {

            ///////////////////////////////////// Session for creating rows

//            Instructor tempInstructor = new Instructor("Irinka", "Queen", "queen@example.com");
//            InstructorDetail detail = new InstructorDetail("http://youtube.com/hahah", "sleeping");
//            tempInstructor.setInstructorDetail(detail);
//
//            Instructor tempInstructor2 = new Instructor("Irinka2", "Queen2", "queen@example.com");
//            InstructorDetail detail2 = new InstructorDetail("http://youtube.com/hahah2", "sleeping");
//            tempInstructor2.setInstructorDetail(detail2);

            Session session = factory.getCurrentSession();

            try {
//                session.beginTransaction();
//
//                session.save(tempInstructor);
//                session.save(tempInstructor2);
//
//                session.getTransaction().commit();
//
//                session = factory.getCurrentSession();
//                session.beginTransaction();
//
//                int theId = 1;
//                Instructor instructor = session.get(Instructor.class, theId);
//
//                Course course1 = new Course("algebra");
//                Course course2 = new Course("gaming");
//                Course course3 = new Course("cooking");
//
//
//                course1.addReview(new Review("wow this is more interesting than i thought"));
//                course1.addReview(new Review("la la la"));
//                course3.addReview(new Review("mmmmmmmmm"));
//
//                instructor.addCourse(course1);
//                instructor.addCourse(course2);
//                instructor.addCourse(course3);
//
//                session.save(course1);
//                session.save(course2);
//                session.save(course3);
//
//                session.getTransaction().commit();

                session = factory.getCurrentSession();
                session.beginTransaction();

                int theId = 10;
                Course tempCourse = session.get(Course.class, theId);

                System.out.println("Reviews : " + tempCourse.getReviews());

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
