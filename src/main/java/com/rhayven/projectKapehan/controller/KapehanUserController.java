package com.rhayven.projectKapehan.controller;

import com.rhayven.projectKapehan.objects.KapehanUser;
import com.rhayven.projectKapehan.service.KapehanUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for handling user login and logout functionality in the Kapehan application.
 * Provides endpoints for rendering the login page, handling login attempts,
 * and logging out users.
 */
@Controller
public class KapehanUserController {

    @Autowired
    KapehanUserService kapehanUserService;

    /**
     * Displays the login page.
     *
     * @param model the model to be passed to the view
     * @return the login view name
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new KapehanUser());
        return "login";
    }

    /**
     * Handles user login attempts.
     *
     * Validates the user input, checks the credentials against stored users,
     * and establishes a session for the user if successful.
     *
     * @param formUser the user entered credentials
     * @param bindingResult validation results for the input
     * @param session the HTTP session to store the logged-in user
     * @param model the model to pass error messages to the view
     * @return the view name based on the success or failure of the login attempt
     */
    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Valid KapehanUser formUser, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        KapehanUser foundUser = kapehanUserService.findByUsername(formUser.getUsername());
        if (foundUser != null && new BCryptPasswordEncoder().matches(formUser.getPassword(), foundUser.getPassword())) {
            session.setAttribute("user", foundUser);
            return "redirect:/";
        } else {
            String error = "Invalid credentials";
            model.addAttribute("error", error);
        }
        return "login";
    }

    /**
     * Logs out the user by invalidating the session.
     *
     * @param session the HTTP session to invalidate
     * @return the redirect URL for the login page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
