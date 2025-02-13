package com.Rhayven;

public class Main {
    public static void main(String[] args) {
        String[] espressoFlavors = {"Chocolate", "Nutty"};
        String[] latteFlavors = {"Creamy", "Caramel"};

        Coffee coffee1 = new Coffee("Espresso", "Arabica", "Small", 50.00, "Dark", "Colombia", false, 20, espressoFlavors, "Espresso");

        // Display initial details
        System.out.println("===== Initial Coffee Details =====");
        coffee1.displayInfo();
        System.out.println(coffee1.describe());

        // Apply discount and show updated price
        System.out.println("----------------------------------");
        double newPrice1 = coffee1.discount(10);
        System.out.println("Espresso Price after the discount: ₱" + newPrice1);

        // Modify
        coffee1.addFlavor("Vanilla");
        coffee1.setDecaf(false);
        coffee1.changeRoastLevel("Light");
        coffee1.updateStock(5);

        // Display updated details
        System.out.println("===== Updated Coffee Details =====");
        coffee1.displayInfo();
        System.out.println(coffee1.describe());
        System.out.println("======================================================");
        System.out.println();
        System.out.println();


        Coffee coffee2 = new Coffee("Latte", "Robusta", "Large", 70.00, "Medium", "Brazil", false, 51, latteFlavors, "Drip");

        // Display initial details
        System.out.println("===== Initial Coffee Details =====");
        coffee2.displayInfo();
        System.out.println(coffee2.describe());
        System.out.println("----------------------------------");

        // Apply discount and show updated price
        double newPrice2 = coffee2.discount(10);
        System.out.println("Latte Price after the discount: ₱" + newPrice2);
        System.out.println("----------------------------------");

        // Modify
        coffee2.addFlavor("Cinnamon");
        coffee2.setDecaf(true);
        coffee2.changeRoastLevel("Dark");
        coffee2.updateStock(2);

        // Display updated details
        System.out.println("===== Updated Coffee Details =====");
        coffee2.displayInfo();
        System.out.println(coffee2.describe());
        System.out.println("======================================================");
    }
}
