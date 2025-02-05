package com.Rhayven;

public class Coffee {
    private String name;
    private String type;
    private String size;
    private double price;
    private String roastLevel;
    private String origin;
    private boolean isDecaf;
    private int stock;
    private String[] flavorNotes;
    private String brewMethod;

    // Constructor
    public Coffee(String name, String type, String size, double price, String roastLevel, String origin,
                  boolean isDecaf, int stock, String[] flavorNotes, String brewMethod) {
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

    public double calculatePrice(String size) {
        switch (size.toLowerCase()) {
            case "small":
                return price;
            case "medium":
                return price + 15;
            case "large":
                return price + 30;
            default:
                return price;
        }
    }

    public boolean checkStock() {
        return stock > 0;
    }

    public void addFlavor(String note) {
        String[] newFlavor = new String[flavorNotes.length + 1];
        for (int i = 0; i < flavorNotes.length; i++) {
            newFlavor[i] = flavorNotes[i];
        }
        newFlavor[flavorNotes.length] = note;
        flavorNotes = newFlavor;
    }

    public void updateStock(int quantity) {
        stock += quantity;
    }

    public String describe() {
        StringBuilder description = new StringBuilder("A " + roastLevel + " roast " + name + " with ");
        for (int i = 0; i < flavorNotes.length; i++) {
            description.append(flavorNotes[i]);
            if (i < flavorNotes.length - 1) {
                description.append(", ");
            }
        }
        description.append(" notes.");
        return description.toString();
    }

    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }

    public void changeRoastLevel(String newRoastLevel) {
        this.roastLevel = newRoastLevel;
    }

    public void discount(double percentage) {
        price -= price * (percentage / 100);
    }

    public void displayInfo() {
        System.out.println("Coffee: " + name);
        System.out.println("Type: " + type);
        System.out.println("Size: " + size);
        System.out.println("Price: ₱" + price);
        System.out.println("Roast Level: " + roastLevel);
        System.out.println("Origin: " + origin);
        System.out.println("Decaf: " + (isDecaf ? "Yes" : "No"));
        System.out.println("Stock: " + stock);

        System.out.print("Flavor Notes: ");
        for (int i = 0; i < flavorNotes.length; i++) {
            System.out.print(flavorNotes[i]);
            if (i < flavorNotes.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        System.out.println("Brew Method: " + brewMethod);
    }
}
