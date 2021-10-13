package com.learninghibernate.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;

public class JavaDBC {

    public static void main(String[] args) {
        String jdbcurl = "jdbc:mysql://localhost:3306/movie-many-to-many?useSSL=false&serverTimezone=UTC";
        String user = "user";
        String pass = "password";

        try {
            System.out.println("Connecting to database: " + jdbcurl);

            Connection myConn = DriverManager.getConnection(jdbcurl, user, pass);

            System.out.println("Connection successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
