package com.rhayven.projectKapehan.models;

import jakarta.validation.constraints.*;
import java.util.List;

/**
 * Represents a coffee item with various properties including name, type,
 * size, price, roast level, origin, and other attributes.
 */
public class Coffee {

    private int id;

    @Size(min = 2, max = 50, message = "Name should have 2 to 50 characters")
    private String name;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Size is required")
    private String size;

    @DecimalMin(value = "0.01", message = "Price is required, No less than 0")
    private double price;

    @NotBlank(message = "Roast level is required")
    private String roastLevel;

    @Size(max = 100, message = "Less than 100 characters only")
    private String origin;

    private boolean decaf;

    @Min(value = 0, message = "Stock is required. Stock must be greater than 0")
    private int stock;

    private List<String> flavorNotes;

    @NotBlank(message = "Brew method is required")
    private String brewMethod;

    private String coffeePicture;

    /**
     * Default constructor.
     */
    public Coffee() {}

    /**
     * Constructs a coffee with all properties.
     *
     * @param id             the coffee ID
     * @param name           the name of the coffee
     * @param type           the type of coffee (e.g., espresso, latte)
     * @param size           the serving size
     * @param price          the price
     * @param roastLevel     the roast level (e.g., light, medium)
     * @param origin         the origin of the coffee beans
     * @param decaf          true if decaffeinated
     * @param stock          number of items in stock
     * @param flavorNotes    list of flavor notes
     * @param brewMethod     brewing method (e.g., French press)
     * @param coffeePicture  image path or URL
     */
    public Coffee(int id, String name, String type, String size, double price,
                  String roastLevel, String origin, boolean decaf, int stock,
                  List<String> flavorNotes, String brewMethod, String coffeePicture) {
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
        this.coffeePicture = coffeePicture;
    }

    /** @return coffee ID */
    public int getId() { return id; }

    /** @param id sets coffee ID */
    public void setId(int id) { this.id = id; }

    /** @return coffee name */
    public String getName() { return name; }

    /** @param name sets coffee name */
    public void setName(String name) { this.name = name; }

    /** @return coffee type */
    public String getType() { return type; }

    /** @param type sets coffee type */
    public void setType(String type) { this.type = type; }

    /** @return serving size */
    public String getSize() { return size; }

    /** @param size sets serving size */
    public void setSize(String size) { this.size = size; }

    /** @return coffee price */
    public double getPrice() { return price; }

    /** @param price sets coffee price */
    public void setPrice(double price) { this.price = price; }

    /** @return roast level */
    public String getRoastLevel() { return roastLevel; }

    /** @param roastLevel sets roast level */
    public void setRoastLevel(String roastLevel) { this.roastLevel = roastLevel; }

    /** @return origin of the coffee */
    public String getOrigin() { return origin; }

    /** @param origin sets origin of the coffee */
    public void setOrigin(String origin) { this.origin = origin; }

    /** @return true if coffee is decaf */
    public boolean isDecaf() { return decaf; }

    /** @param decaf sets decaf status */
    public void setDecaf(boolean decaf) { this.decaf = decaf; }

    /** @return stock quantity */
    public int getStock() { return stock; }

    /** @param stock sets stock quantity */
    public void setStock(int stock) { this.stock = stock; }

    /** @return list of flavor notes */
    public List<String> getFlavorNotes() { return flavorNotes; }

    /** @param flavorNotes sets flavor notes */
    public void setFlavorNotes(List<String> flavorNotes) { this.flavorNotes = flavorNotes; }

    /** @return brew method */
    public String getBrewMethod() { return brewMethod; }

    /** @param brewMethod sets brew method */
    public void setBrewMethod(String brewMethod) { this.brewMethod = brewMethod; }

    /** @return coffee picture path or URL */
    public String getCoffeePicture() { return coffeePicture; }

    /** @param coffeePicture sets coffee picture */
    public void setCoffeePicture(String coffeePicture) { this.coffeePicture = coffeePicture; }
}
