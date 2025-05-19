package com.rhayven.projectKapehan.controller;

import jakarta.servlet.http.HttpSession;
import com.rhayven.projectKapehan.models.Coffee;
import com.rhayven.projectKapehan.models.KapehanUser;
import com.rhayven.projectKapehan.service.CoffeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * CoffeeController handles HTTP requests related to managing coffee entries,
 * including listing, searching, adding, editing, and deleting coffees.
 */
@Controller
public class CoffeeController {

    private final CoffeeService coffeeService;

    /**
     * Constructor-based dependency injection for CoffeeService.
     *
     * @param coffeeService the service responsible for coffee operations
     */
    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    /**
     * Displays the home layout.
     *
     * @param model the Spring UI model
     * @return the master layout view
     */
    @GetMapping("/home")
    public String home(Model model) {
        return "layouts/master";
    }

    /**
     * Displays the list of coffees with optional search.
     *
     * @param search the search keyword
     * @param session current HTTP session
     * @param model the Spring UI model
     * @return the main index page if user is logged in, otherwise redirect to login
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, HttpSession session, Model model) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("coffeeList", coffeeService.searchCoffee(search));
        model.addAttribute("activeMenu", "home");
        return "pages/index";
    }

    /**
     * Displays the catalog page with coffee list and search functionality.
     *
     * @param search the search keyword
     * @param session current HTTP session
     * @param model the Spring UI model
     * @return the catalog page view or redirect to login
     */
    @GetMapping("/catalog")
    public String catalog(@RequestParam(defaultValue = "") String search, HttpSession session, Model model) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("coffeeList", coffeeService.searchCoffee(search));
        model.addAttribute("activeMenu", "catalog");
        return "pages/catalog";
    }

    /**
     * Deletes a coffee by its ID.
     *
     * @param id the ID of the coffee to delete
     * @param session current HTTP session
     * @return redirect to home if successful or to login if not authenticated
     */
    @GetMapping("/delete")
    public String deleteCoffee(@RequestParam("id") int id, HttpSession session) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        coffeeService.deleteCoffee(id);
        return "redirect:/";
    }

    /**
     * Displays the form to add a new coffee.
     *
     * @param model the Spring UI model
     * @param session current HTTP session
     * @return the form page for adding coffee or redirect to login
     */
    @GetMapping("/add")
    public String addCoffeeForm(Model model, HttpSession session) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("coffee", new Coffee());
        model.addAttribute("activeMenu", "add");
        return "pages/new";
    }

    /**
     * Saves a new coffee entry from form input, including optional image upload.
     *
     * @param coffee the coffee object submitted from the form
     * @param result validation result
     * @param coffeePicture uploaded image file
     * @param session current HTTP session
     * @return redirect to home or return to form if validation fails
     */
    @PostMapping("/save")
    public String storeSave(@Valid @ModelAttribute("coffee") Coffee coffee, BindingResult result,
                            @RequestParam("coffeePic") MultipartFile coffeePicture, HttpSession session) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "pages/new";
        }

        // Handle image upload
        if (!coffeePicture.isEmpty()) {
            String path = "data/coffee_pictures/";
            File uploadFolder = new File(path);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String fileName = UUID.randomUUID() + coffeePicture.getOriginalFilename().substring(coffeePicture.getOriginalFilename().lastIndexOf("."));
            try {
                coffeePicture.transferTo(new File(uploadFolder.getAbsolutePath() + File.separator + fileName));
                coffee.setCoffeePicture(fileName);
            } catch (IOException e) {
                System.out.println("File upload error: " + e.getMessage());
            }
        }

        coffeeService.addCoffee(coffee);
        return "redirect:/";
    }

    /**
     * Displays the edit form for an existing coffee.
     *
     * @param id the ID of the coffee to edit
     * @param model the Spring UI model
     * @param session current HTTP session
     * @return the edit form view or redirect to login
     */
    @GetMapping("/edit")
    public String editCoffee(@RequestParam("id") int id, Model model, HttpSession session) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Coffee coffee = coffeeService.getCoffee(id);
        if (coffee != null) {
            model.addAttribute("coffee", coffee);
            return "pages/edit";
        }

        return "redirect:/";
    }

    /**
     * Updates an existing coffee entry after editing.
     *
     * @param coffee the updated coffee object
     * @param result validation result
     * @param session current HTTP session
     * @return redirect to home or return to edit form if validation fails
     */
    @PostMapping("/update")
    public String storeUpdate(@Valid @ModelAttribute("coffee") Coffee coffee, BindingResult result, HttpSession session) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "pages/edit";
        }


        Coffee existingCoffee = coffeeService.getCoffee(coffee.getId());
        if (existingCoffee != null) {
            coffee.setCoffeePicture(existingCoffee.getCoffeePicture());
            coffeeService.updateCoffee(coffee.getId(), coffee);
        }


        return "redirect:/";
    }

    /**
     * Displays detailed view of a coffee by its ID.
     *
     * @param id the ID of the coffee to view
     * @param model the Spring UI model
     * @param session current HTTP session
     * @return view page for a single coffee or redirect to login
     */
    @GetMapping("/coffee/{id}")
    public String view(@PathVariable int id, Model model, HttpSession session) {
        KapehanUser user = (KapehanUser) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Coffee coffee = coffeeService.getCoffee(id);
        model.addAttribute("coffee", coffee);
        return "pages/coffee";
    }
}

