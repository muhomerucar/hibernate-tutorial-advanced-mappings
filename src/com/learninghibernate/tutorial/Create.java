package com.learninghibernate.tutorial;

import com.learninghibernate.tutorial.entities.Director;
import com.learninghibernate.tutorial.entities.Movie;
import com.learninghibernate.tutorial.entities.Review;
import com.learninghibernate.tutorial.entities.Star;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Create {

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

            //Step 5: create objects and save into table in database...

            Director directorMartinScorsese = new Director("Martin","Scorsese","USA");
            session.save(directorMartinScorsese);

            Movie casinoMovie = new Movie("Casino",directorMartinScorsese);
            session.save(casinoMovie);

            Star robertDeNiro = new Star("Robert","De Niro","USA");
            session.save(robertDeNiro);

            Star joePesci = new Star("Joe","Pesci","USA");
            session.save(joePesci);

            Star sharonStone = new Star("Sharon","Stone","USA");
            session.save(sharonStone);

            casinoMovie.addStar(robertDeNiro);
            casinoMovie.addStar(joePesci);
            casinoMovie.addStar(sharonStone);

            Review review = new Review("It's a masterpieceé!!");
            session.save(review);
            casinoMovie.addReview(review);

            Review review1 = new Review("It is really good movie except Sharon's acting skills...");
            session.save(review1);
            casinoMovie.addReview(review1);

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
