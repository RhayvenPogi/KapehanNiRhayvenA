package com.rhayven.projectKapehan;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing coffee records.
 * Handles CRUD operations and file persistence.
 */
public class CoffeeService {
    private ArrayList<Coffee> coffeeList;
    private final String FILE_NAME = "database.csv";

    /**
     * Constructor initializes the coffee list and loads data from disk.
     */
    public CoffeeService(){
        coffeeList = new ArrayList<>();
        readFromDisk();
    }

    /**
     * Retrieves all coffee records.
     * @return List of all Coffee objects.
     */
    public ArrayList<Coffee> getCoffees() {
        return coffeeList;
    }

    /**
     * Deletes a coffee record by ID and updates the IDs of remaining records.
     * @param id The ID of the coffee to be deleted.
     */
    public void deleteCoffee(int id){
        coffeeList.removeIf(coffee -> coffee.getId() == id);
        for (int i = 0; i < coffeeList.size(); i++) {
            coffeeList.get(i).setId(i + 1);
        }
        writeToDisk();
    }

    /**
     * Searches for coffee records matching the given keyword.
     * @param keyword The search keyword.
     * @return List of Coffee objects that match the search criteria.
     */
    public List<Coffee> searchCoffee(String keyword){
        if(keyword.trim().isEmpty()){
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
                        || (List.of("true", "decaf", "yes").contains(keyword.toLowerCase()) && coffee.isDecaf())
                        || (List.of("false", "regular", "no").contains(keyword.toLowerCase()) && !coffee.isDecaf())
                ).collect(Collectors.toList());
    }

    /**
     * Retrieves a coffee record by ID.
     * @param id The ID of the coffee.
     * @return The Coffee object if found, otherwise null.
     */
    public Coffee getCoffee(int id){
        for(Coffee coffee: coffeeList){
            if(coffee.getId() == id)
                return coffee;
        }
        return null;
    }

    /**
     * Updates an existing coffee record.
     * @param id The ID of the coffee to be updated.
     * @param update The new Coffee object.
     */
    public void updateCoffee(int id, Coffee update){
        for(int i = 0; i < coffeeList.size(); i++){
            if(coffeeList.get(i).getId() == id){
                coffeeList.set(i, update);
                writeToDisk();
                break;
            }
        }
    }

    /**
     * Adds a new coffee record.
     * @param coffee The Coffee object to be added.
     */
    public void addCoffee(Coffee coffee){
        coffeeList.add(coffee);
        writeToDisk();
    }

    /**
     * Gets the last ID in the coffee list.
     * @return The last coffee ID, or 0 if the list is empty.
     */
    public int getLastId(){
        if(coffeeList.isEmpty()){
            return 0;
        }
        return coffeeList.getLast().getId();
    }

    /**
     * Saves the coffee list to a CSV file.
     */
    public void writeToDisk(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Coffee coffee : coffeeList){
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
                        + coffee.getBrewMethod()
                );
                bw.newLine();
            }
        } catch (IOException e){
            System.out.println("Oh no! Error: " + e.getMessage());
        }
    }

    /**
     * Reads coffee records from a CSV file and loads them into the coffee list.
     */
    public void readFromDisk(){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            System.out.println("File not found.");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                //if (data.length < 11) continue;

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

                coffeeList.add(coffee);
            }
        }catch (IOException e){
            System.out.println("Oh no! Error: " + e.getMessage());
        }
    }
}
