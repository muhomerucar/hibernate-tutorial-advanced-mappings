package com.learninghibernate.tutorial;

import com.learninghibernate.tutorial.entities.Director;
import com.learninghibernate.tutorial.entities.Movie;
import com.learninghibernate.tutorial.entities.Review;
import com.learninghibernate.tutorial.entities.Star;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Update {
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

            //Step 5: update objects...
            List<Movie> movies = session.createQuery("from Movie").getResultList();
            System.out.println(movies);

            int movieId = 1011;
            Movie movie = session.get(Movie.class,movieId);
            movie.setTitle("Casino (1995)");
            System.out.println(movie);

            int starId = 6;
            Star star = session.get(Star.class,starId);
            star.setNationality("London, England");
            System.out.println(star);

            // other way
            session.createQuery("update Movie set title ='Casino' where id = 1011").executeUpdate();
            session.createQuery("update Star set nationality = 'UK' where id = 6").executeUpdate();


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
