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

/**
 * CoffeeController handles requests for managing coffee records.
 */
@Controller
public class CoffeeController {

    private final CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    /**
     * Displays the list of coffees with optional search functionality.
     *
     * @param search The search keyword to filter coffee records.
     * @param model  Model to add the coffee list attribute.
     * @return The view name for the coffee list page.
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search,  HttpSession session, Model model) {
        KapehanUser user= (KapehanUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        model.addAttribute("coffeeList", coffeeService.searchCoffee(search));
        return "index";
    }

    /**
     * Deletes a coffee by its ID.
     *
     * @param id Coffee ID to delete.
     * @return Redirects to coffee list.
     */
    @GetMapping("/delete")
    public String deleteCoffee(@RequestParam("id") int id,  HttpSession session) {
        KapehanUser user= (KapehanUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        coffeeService.deleteCoffee(id);
        return "redirect:/";
    }

    /**
     * Displays form for adding a new coffee.
     *
     * @param model Model to add empty coffee object.
     * @return View name for new coffee form.
     */
    @GetMapping("/add")
    public String addCoffeeForm(Model model,  HttpSession session) {
        KapehanUser user= (KapehanUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        model.addAttribute("coffee", new Coffee());
        return "new";
    }

    /**
     * Saves a new coffee entry with validation.
     *
     * @param coffee The coffee object from form.
     * @param result Binding result for validation.
     * @return Redirects to coffee list or returns to form if errors.
     */
    @PostMapping("/save")
    public String storeSave(@Valid @ModelAttribute("coffee") Coffee coffee,  HttpSession session, BindingResult result, Model model) {
        KapehanUser user= (KapehanUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "new"; // return to form if errors are present
        }


        coffeeService.addCoffee(coffee);
        return "redirect:/";
    }

    /**
     * Displays form for editing an existing coffee.
     *
     * @param id    Coffee ID to edit.
     * @param model Model to add coffee attribute.
     * @return View name for edit form.
     */
    @GetMapping("/edit")
    public String editCoffee(@RequestParam("id") int id, Model model,  HttpSession session) {
        KapehanUser user= (KapehanUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        Coffee coffee = coffeeService.getCoffee(id);
        if (coffee != null) {
            model.addAttribute("coffee", coffee);
            return "edit";
        }
        return "redirect:/"; // Redirect to main page if coffee not found
    }

    /**
     * Updates an existing coffee entry with validation.
     *
     * @param coffee Coffee object to update.
     * @param result Binding result for validation.
     * @return Redirects to coffee list or returns to form if errors.
     */
    @PostMapping("/update")
    public String storeUpdate(@Valid @ModelAttribute("coffee") Coffee coffee,Model model, HttpSession session, BindingResult result) {
        KapehanUser user= (KapehanUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("coffee", coffee);
            System.out.println(result.getAllErrors());
            return "edit"; // return to edit form if errors are present
        }

        Coffee existingCoffee = coffeeService.getCoffee(coffee.getId());
        if (existingCoffee != null) {
            // save the object if form is valid or passes all rules
            coffeeService.updateCoffee(coffee.getId(), coffee);
        }

        return "redirect:/";
    }
}
