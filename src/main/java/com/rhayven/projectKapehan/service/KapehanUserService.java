package com.rhayven.projectKapehan.service;

import com.rhayven.projectKapehan.objects.KapehanUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle user-related operations such as loading, finding,
 * and saving users. The users' data is stored in a CSV file.
 */
@Service
public class KapehanUserService {

    private List<KapehanUser> kapehanUsers;
    private final File file = new File("data/kapehan_users.csv");

    /**
     * Initializes the service by loading the user data from a CSV file.
     * If the file does not exist, it will be created.
     * This method is called after the constructor and after the bean properties
     * have been set.
     *
     * @throws IOException if an I/O error occurs while reading the file.
     */
    @PostConstruct
    public void init() throws IOException {
        kapehanUsers = new ArrayList<>();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Ensure "data" folder exists
                file.createNewFile(); // Create file if it doesn't exist
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        reader.readLine(); // Skip the header line
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            KapehanUser kapehanUser = new KapehanUser();
            kapehanUser.setUsername(data[0]);
            kapehanUser.setPassword(data[1]);
            kapehanUsers.add(kapehanUser);
        }
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for.
     * @return the {@link KapehanUser} object if found, otherwise null.
     */
    public KapehanUser findByUsername(String username) {
        return kapehanUsers.stream()
                .filter(kapehanUser -> kapehanUser.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }


    public void save(KapehanUser kapehanUser) {

    }
}
