package com.rhayven.projectKapehan.objects;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a user in the Kapehan application. Contains the user's username
 * and hashed password.
 */
public class KapehanUser {

    @NotBlank(message = "Username is required")
    private String username;

    private String password; // hashed password

    /**
     * Gets the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     *
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the hashed password of the user.
     *
     * @return the hashed password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the hashed password for the user.
     *
     * @param password the hashed password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
