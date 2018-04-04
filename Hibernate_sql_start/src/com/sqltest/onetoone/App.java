package com.sqltest.onetoone;

import com.sqltest.general.DateUtils;
import com.sqltest.general.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {

        try( SessionFactory factory = new Configuration()
                .configure("hibernate_onetoone_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
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

                InstructorDetail resultDetail = session.get(InstructorDetail.class, 1);

                session.delete(tempInstructor2);
                session.delete(resultDetail);

                session.getTransaction().commit();


                session = factory.getCurrentSession();
                session.beginTransaction();


                int theId = 1;
                Instructor instructor = session.get(Instructor.class, theId);

                session.getTransaction().commit();


            } finally {
                //do we need to close every time?
                session.close();
            }
            //


        }

    }
}
