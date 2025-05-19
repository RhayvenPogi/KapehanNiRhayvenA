package com.rhayven.projectKapehan.service;

import com.rhayven.projectKapehan.models.Coffee;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing coffee records.
 * Provides CRUD operations, search functionality, and file persistence using CSV.
 */
@Service
public class CoffeeService {
    private ArrayList<Coffee> coffeeList;
    private final String FILE_NAME = "database.csv";

    /**
     * Initializes the coffee list and loads data from the CSV file.
     */
    public CoffeeService() {
        coffeeList = new ArrayList<>();
        readFromDisk();
    }

    /**
     * Retrieves all coffee records.
     * @return list of all coffees
     */
    public ArrayList<Coffee> getCoffees() {
        return coffeeList;
    }

    /**
     * Deletes a coffee by ID and reassigns IDs sequentially.
     * @param id the ID of the coffee to delete
     */
    public void deleteCoffee(int id) {
        coffeeList.removeIf(coffee -> coffee.getId() == id);
        for (int i = 0; i < coffeeList.size(); i++) {
            coffeeList.get(i).setId(i + 1);
        }
        writeToDisk();
    }

    /**
     * Searches for coffee records that match the given keyword in any field.
     * @param keyword the keyword to search
     * @return list of matching coffee records
     */
    public List<Coffee> searchCoffee(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return coffeeList;
        }

        return coffeeList.stream()
                .filter(coffee -> String.valueOf(coffee.getId()).equals(keyword)
                        || coffee.getName().toLowerCase().contains(keyword.toLowerCase())
                        || coffee.getType().toLowerCase().contains(keyword.toLowerCase())
                        || coffee.getSize().toLowerCase().contains(keyword.toLowerCase())
                        || coffee.getOrigin().toLowerCase().contains(keyword.toLowerCase())
                        || coffee.getRoastLevel().toLowerCase().contains(keyword.toLowerCase())
                        || coffee.getBrewMethod().toLowerCase().contains(keyword.toLowerCase())
                        || String.join(" ", coffee.getFlavorNotes()).toLowerCase().contains(keyword.toLowerCase())
                        || String.valueOf(coffee.getPrice()).contains(keyword)
                        || (List.of("true", "decaffeinated", "decaf", "yes").contains(keyword.toLowerCase()) && coffee.isDecaf())
                        || (List.of("false", "caffeinated", "regular", "no").contains(keyword.toLowerCase()) && !coffee.isDecaf()))
                .collect(Collectors.toList());
    }

    /**
     * Gets a coffee by its ID.
     * @param id the coffee ID
     * @return matching coffee or null if not found
     */
    public Coffee getCoffee(int id) {
        for (Coffee coffee : coffeeList) {
            if (coffee.getId() == id)
                return coffee;
        }
        return null;
    }

    /**
     * Updates an existing coffee record.
     * @param id the ID of the coffee to update
     * @param update the updated coffee object
     */
    public void updateCoffee(int id, Coffee update) {
        for (int i = 0; i < coffeeList.size(); i++) {
            if (coffeeList.get(i).getId() == id) {
                coffeeList.set(i, update);
                writeToDisk();
                break;
            }
        }
    }

    /**
     * Adds a new coffee with the next available ID.
     * @param coffee the coffee object to add
     */
    public void addCoffee(Coffee coffee) {
        coffee.setId(getLastId() + 1);
        coffeeList.add(coffee);
        writeToDisk();
    }

    /**
     * Gets the highest current coffee ID.
     * @return the last coffee ID, or 0 if list is empty
     */
    public int getLastId() {
        if (coffeeList.isEmpty()) {
            return 0;
        }
        return coffeeList.getLast().getId();
    }

    /**
     * Saves the current coffee list to disk as CSV.
     */
    public void writeToDisk() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Coffee coffee : coffeeList) {
                bw.write(coffee.getId() + ","
                        + coffee.getName() + ","
                        + coffee.getType() + ","
                        + coffee.getSize() + ","
                        + coffee.getPrice() + ","
                        + coffee.getRoastLevel() + ","
                        + coffee.getOrigin() + ","
                        + coffee.isDecaf() + ","
                        + coffee.getStock() + ","
                        + String.join("|", coffee.getFlavorNotes()) + ","
                        + coffee.getBrewMethod() + ","
                        + coffee.getCoffeePicture());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oh no! Error: " + e.getMessage());
        }
    }

    /**
     * Loads coffee records from the CSV file.
     */
    public void readFromDisk() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 12) continue;

                Coffee coffee = new Coffee();
                coffee.setId(Integer.parseInt(data[0]));
                coffee.setName(data[1]);
                coffee.setType(data[2]);
                coffee.setSize(data[3]);
                coffee.setPrice(Double.parseDouble(data[4]));
                coffee.setRoastLevel(data[5]);
                coffee.setOrigin(data[6]);
                coffee.setDecaf(Boolean.parseBoolean(data[7]));
                coffee.setStock(Integer.parseInt(data[8]));
                coffee.setFlavorNotes(Arrays.asList(data[9].split("\\|")));
                coffee.setBrewMethod(data[10]);
                coffee.setCoffeePicture(data[11]);

                coffeeList.add(coffee);
            }
        } catch (IOException e) {
            System.out.println("Oh no! Error: " + e.getMessage());
        }
    }
}
