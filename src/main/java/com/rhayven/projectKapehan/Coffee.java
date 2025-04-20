package com.rhayven.projectKapehan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Coffee {

    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Size is required")
    private String size;

    private double price;

    @NotBlank(message = "Roast level is required")
    private String roastLevel;

    private String origin;

    private boolean decaf;

    private int stock;

    @Size(min = 1, message = "Please select at least one flavor note")
    private List<String> flavorNotes;

    @NotBlank(message = "Brew method is required")
    private String brewMethod;

    // Constructors
    public Coffee() {}

    public Coffee(int id, String name, String type, String size, double price,
                  String roastLevel, String origin, boolean decaf, int stock,
                  List<String> flavorNotes, String brewMethod) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.roastLevel = roastLevel;
        this.origin = origin;
        this.decaf = decaf;
        this.stock = stock;
        this.flavorNotes = flavorNotes;
        this.brewMethod = brewMethod;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getRoastLevel() { return roastLevel; }
    public void setRoastLevel(String roastLevel) { this.roastLevel = roastLevel; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public boolean isDecaf() { return decaf; }
    public void setDecaf(boolean decaf) { this.decaf = decaf; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public List<String> getFlavorNotes() { return flavorNotes; }
    public void setFlavorNotes(List<String> flavorNotes) { this.flavorNotes = flavorNotes; }

    public String getBrewMethod() { return brewMethod; }
    public void setBrewMethod(String brewMethod) { this.brewMethod = brewMethod; }
}
