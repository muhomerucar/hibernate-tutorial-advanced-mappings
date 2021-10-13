package com.learninghibernate.tutorial;

import com.learninghibernate.tutorial.entities.Director;
import com.learninghibernate.tutorial.entities.Movie;
import com.learninghibernate.tutorial.entities.Review;
import com.learninghibernate.tutorial.entities.Star;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Delete {
    public static void main(String[] args) {
        //Step 1: Create session factory

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Star.class)
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //Step 2: create session
        Session session = factory.getCurrentSession();

        try {

            //Step 4: start a transaction
            session.beginTransaction();

            //Step 5: Delete objects from table in database...

            int id = 7;
            Star star = session.get(Star.class,id);
            session.delete(star);

            //Step 6: commit the transaction
            session.getTransaction().commit();
            System.out.println("Completed!");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            factory.close();
        }
    }
}
