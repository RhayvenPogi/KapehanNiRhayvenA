package com.rhayven.projectKapehan;

import java.util.List;

public class Coffee {
    //instance vars
    private int id;
    private String name;
    private String type;
    private String size;
    private double price;
    private String roastLevel;
    private String origin;
    private boolean isDecaf;
    private int stock;
    private List<String> flavorNotes;
    private String brewMethod;

    //parameterless or default constructor
    public Coffee(){

    }

    /**
     * Constructs a Coffee object with specified properties.
     * @param id The unique identifier of the coffee (int).
     * @param name The name of the coffee (String).
     * @param type The type of coffee, e.g., Arabica, Robusta (String).
     * @param size The size of the coffee, e.g., Small, Medium, Large (String).
     * @param price The price of the coffee (double).
     * @param roastLevel The roast level, e.g., Light, Medium, Dark (String).
     * @param origin The origin country of the coffee beans (String).
     * @param isDecaf Indicates whether the coffee is decaffeinated (boolean).
     * @param stock The available stock quantity (int).
     * @param flavorNotes A list of flavor notes describing the coffee’s taste (List<String>).
     * @param brewMethod The recommended brewing method, e.g., French press, pour-over (String).
     */
    public Coffee(int id, String name, String type, String size, double price, String roastLevel, String origin, boolean isDecaf, int stock, List<String> flavorNotes, String brewMethod) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.roastLevel = roastLevel;
        this.origin = origin;
        this.isDecaf = isDecaf;
        this.stock = stock;
        this.flavorNotes = flavorNotes;
        this.brewMethod = brewMethod;
    }

    public int getId() {
        return id; }
    public void setId(int id) {
        this.id = id; }

    public String getName() {
        return name; }
    public void setName(String name) {
        this.name = name; }

    public String getType() {
        return type; }
    public void setType(String type) {
        this.type = type; }

    public String getSize() {
        return size; }
    public void setSize(String size) {
        this.size = size; }

    public double getPrice() {
        return price; }
    public void setPrice(double price) {
        this.price = price; }

    public String getRoastLevel() {
        return roastLevel; }
    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel; }

    public String getOrigin() {
        return origin; }
    public void setOrigin(String origin) {
        this.origin = origin; }

    public boolean isDecaf() {
        return isDecaf; }
    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf; }

    public int getStock() {
        return stock; }
    public void setStock(int stock) {
        this.stock = stock; }

    public List<String> getFlavorNotes() {
        return flavorNotes; }
    public void setFlavorNotes(List<String> flavorNotes) {
        this.flavorNotes = flavorNotes;
    }

    public String getBrewMethod() {
        return brewMethod; }
    public void setBrewMethod(String brewMethod) {
        this.brewMethod = brewMethod; }
}