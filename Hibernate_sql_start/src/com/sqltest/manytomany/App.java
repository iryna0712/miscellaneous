package com.sqltest.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        try( SessionFactory factory = new Configuration()
                .configure("hibernate_manytomany.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
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

                Course course1 = new Course("algebra");
                Course course2 = new Course("gaming");
                Course course3 = new Course("cooking");


                course1.addReview(new Review("wow this is more interesting than i thought"));
                course1.addReview(new Review("la la la"));
                course3.addReview(new Review("mmmmmmmmm"));

                instructor.addCourse(course1);
                instructor.addCourse(course2);
                instructor.addCourse(course3);

                session.save(course1);
                session.save(course2);
                session.save(course3);

                Student student1 = new Student("John1", "Doe1", "john1@example.com");
                Student student2 = new Student("John2", "Doe2", "john2@example.com");
                Student student3 = new Student("John3", "Doe3", "john3@example.com");

                course1.addStudent(student1);
                course1.addStudent(student2);

                course2.addStudent(student2);

                course3.addStudent(student1);
                course3.addStudent(student3);

                session.save(student1);
                session.save(student2);
                session.save(student3);

                session.getTransaction().commit();

                session = factory.getCurrentSession();
                session.beginTransaction();

                Student tempStudent = session.get(Student.class, 3);
                System.out.println("Student : " + tempStudent);

                Course course4 = new Course("algebra2");
                Course course5 = new Course("gaming2");
                Course course6 = new Course("cooking2");

                course4.addStudent(tempStudent);
                course5.addStudent(tempStudent);
                course6.addStudent(tempStudent);

                session.save(course4);
                session.save(course5);
                session.save(course6);


                session.getTransaction().commit();
                session.close();

                session = factory.getCurrentSession();
                session.beginTransaction();

                Student tempStudent2 = session.get(Student.class, 3);
                System.out.println("Student with courses: " + tempStudent2.getCourses());

                session.getTransaction().commit();
                session.close();

                session = factory.getCurrentSession();
                session.beginTransaction();

                Course courseToFind = session.get(Course.class, 3);
                session.delete(courseToFind);

                Student studentToFind = session.get(Student.class, 3);
                session.delete(studentToFind);

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
