package com.rhayven.projectKapehan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class CoffeeController {
    CoffeeService coffeeService;

    /**
     * Initializes the coffee list with sample data.
     */
    public CoffeeController() {
        coffeeService = new CoffeeService();


    }

    /**
     * Displays the list of coffees.
     * @param model Model to add coffee list attribute.
     * @return View name for coffee list.
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model) {
        model.addAttribute("coffeeList", coffeeService.searchCoffee(search));
        return "index";
    }

    /**
     * Deletes a coffee by its ID.
     * @param id Coffee ID to delete.
     * @return Redirects to coffee list.
     */
    @GetMapping("/delete")
    public String deleteCoffee(@RequestParam int id) {
        coffeeService.deleteCoffee(id);
        return "redirect:/";
    }

    /**
     * Displays form for adding a new coffee.
     * @return View name for new coffee form.
     */
    @GetMapping("/add")
    public String addCoffeeForm() {
        return "new";
    }

    /**
     * Saves a new coffee entry.
     * @return Redirects to coffee list.
     */
    @PostMapping("/save")
    public String storeSave(@RequestParam String name,
                             @RequestParam String type,
                             @RequestParam String size,
                             @RequestParam double price,
                             @RequestParam String roastLevel,
                             @RequestParam String origin,
                             @RequestParam (defaultValue = "false") boolean isDecaf,
                             @RequestParam int stock,
                             @RequestParam List<String> flavorNotes,
                             @RequestParam String brewMethod) {
        Coffee coffee = new Coffee(coffeeService.getLastId() + 1, name, type, size, price, roastLevel, origin, isDecaf, stock, flavorNotes, brewMethod);
        coffeeService.addCoffee(coffee);
        return "redirect:/";
    }

    /**
     * Displays form for editing an existing coffee.
     * @param id Coffee ID to edit.
     * @param model Model to add coffee attribute.
     * @return View name for edit form.
     */
    @GetMapping("/edit")
    public String editCoffee(@RequestParam int id, Model model) {
        Coffee coffee = coffeeService.getCoffee(id);
        if (coffee != null) {
            model.addAttribute("coffee", coffee);
            return "edit";
        }
        return "redirect:/";
    }

    /**
     * Updates an existing coffee entry.
     * @return Redirects to coffee list.
     */
    @PostMapping("/update")
    public String storeUpdate(@RequestParam int id,
                               @RequestParam String name,
                               @RequestParam String type,
                               @RequestParam String size,
                               @RequestParam double price,
                               @RequestParam String roastLevel,
                               @RequestParam String origin,
                               @RequestParam(defaultValue = "false") boolean isDecaf,
                               @RequestParam int stock,
                               @RequestParam List<String> flavorNotes,
                               @RequestParam String brewMethod) {
        Coffee coffee = coffeeService.getCoffee(id);
        if (coffee != null) {
            coffee.setName(name);
            coffee.setType(type);
            coffee.setSize(size);
            coffee.setPrice(price);
            coffee.setRoastLevel(roastLevel);
            coffee.setOrigin(origin);
            coffee.setDecaf(isDecaf);
            coffee.setStock(stock);
            coffee.setFlavorNotes(flavorNotes);
            coffee.setBrewMethod(brewMethod);

            coffeeService.updateCoffee(id, coffee);
        }
        return "redirect:/";
    }

}
