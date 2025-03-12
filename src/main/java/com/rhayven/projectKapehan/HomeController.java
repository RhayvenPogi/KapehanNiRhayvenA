package com.rhayven.projectKapehan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    private List<Coffee> coffeeList = new ArrayList<>();

    /**
     * Initializes the coffee list with sample data.
     */
    public HomeController() {
        coffeeList.add(new Coffee(1, "Espresso", "Arabica", "Small", 3.50, "Dark", "Ethiopia", false, 10, Arrays.asList("Chocolate", "Nutty"), "Espresso"));
        coffeeList.add(new Coffee(2, "Latte", "Arabica", "Medium", 4.50, "Medium", "Brazil", false, 8, Arrays.asList("Creamy", "Sweet"), "Drip"));
        coffeeList.add(new Coffee(3, "Cappuccino", "Robusta", "Large", 5.00, "Medium", "Colombia", false, 12, Arrays.asList("Fruity", "Bold"), "French Press"));
        coffeeList.add(new Coffee(4, "Mocha", "Arabica", "Medium", 4.75, "Dark", "Guatemala", false, 6, Arrays.asList("Chocolate", "Smooth"), "Espresso"));
        coffeeList.add(new Coffee(5, "Americano", "Robusta", "Large", 3.25, "Light", "Kenya", false, 15, Arrays.asList("Citrus", "Balanced"), "Drip"));
    }

    /**
     * Displays the list of coffees.
     * @param model Model to add coffee list attribute.
     * @return View name for coffee list.
     */
    @GetMapping("/")
    public String getCoffees(Model model) {
        model.addAttribute("coffees", coffeeList);
        return "index";
    }

    /**
     * Deletes a coffee by its ID.
     * @param id Coffee ID to delete.
     * @return Redirects to coffee list.
     */
    @GetMapping("/delete")
    public String deleteCoffee(@RequestParam int id) {
        coffeeList.removeIf(coffee -> coffee.getId() == id);
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
    public String saveCoffee(@RequestParam String name,
                             @RequestParam String type,
                             @RequestParam String size,
                             @RequestParam double price,
                             @RequestParam String roastLevel,
                             @RequestParam String origin,
                             @RequestParam (defaultValue = "false") boolean isDecaf,
                             @RequestParam int stock,
                             @RequestParam List<String> flavorNotes,
                             @RequestParam String brewMethod) {
        int newId = coffeeList.isEmpty() ? 1 : coffeeList.get(coffeeList.size() - 1).getId() + 1;  // Handle empty list scenario
        coffeeList.add(new Coffee(newId, name, type, size, price, roastLevel, origin, isDecaf, stock, flavorNotes, brewMethod));
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
        for (Coffee coffee : coffeeList) {
            if (coffee.getId() == id) {
                model.addAttribute("coffee", coffee);
                return "edit";
            }
        }
        return "redirect:/";
    }

    /**
     * Updates an existing coffee entry.
     * @return Redirects to coffee list.
     */
    @PostMapping("/update")
    public String updateCoffee(@RequestParam int id,
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
        for (Coffee coffee : coffeeList) {
            if (coffee.getId() == id) {
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
                break;
            }
        }
        return "redirect:/";
    }
}
