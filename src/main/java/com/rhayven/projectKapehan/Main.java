package com.rhayven.projectKapehan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The entry point for the Spring Boot application.
 * This class contains the main method to launch the application.
 */
@SpringBootApplication
public class Main {

    /**
     * The main method which starts the Spring Boot application.
     * It runs the Spring application context.
     */
    public static void main(String[] args) {
        //run();
        SpringApplication.run(Main.class, args);
    }

    /**
     * This method is used to demonstrate the BCrypt password hashing process.
     * It generates a hashed version of a plain password and prints it to the console.
     *
     */
    public static void run() {
        String plainPassword = "paextrashotpls";
        String hash = new BCryptPasswordEncoder().encode(plainPassword);
        System.out.println(hash);
    }

}
