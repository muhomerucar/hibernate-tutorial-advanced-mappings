package com.learninghibernate.tutorial;

import com.learninghibernate.tutorial.entities.Director;
import com.learninghibernate.tutorial.entities.Movie;
import com.learninghibernate.tutorial.entities.Review;
import com.learninghibernate.tutorial.entities.Star;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Read {
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

            //Step 5: get objects from table in database...

            int directorId = 1;
            Director tempDirector = session.get(Director.class, directorId);

            System.out.println(tempDirector);

            int starId = 6;
            Star tempStar = session.get(Star.class, starId);
            System.out.println(tempStar);

            int movieId = 1011;
            Movie tempMovie = session.get(Movie.class, movieId);
            System.out.println(tempMovie);
            System.out.println(tempMovie.getReviews());


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
