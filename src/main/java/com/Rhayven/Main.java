package com.Rhayven;

public class Main {
    public static void main(String[] args) {
        String[] espressoFlavors = {"Chocolate", "Nutty"};
        String[] latteFlavors = {"Creamy", "Caramel"};

        Coffee coffee1 = new Coffee("Espresso", "Arabica", "Small", 50.00, "Dark", "Colombia", false, 10, espressoFlavors, "Espresso");
        Coffee coffee2 = new Coffee("Latte", "Robusta", "Medium", 70.00, "Medium", "Brazil", false, 5, latteFlavors, "Drip");

        coffee1.displayInfo();
        System.out.println(coffee1.describe());

        coffee1.discount(10);
        System.out.println("Price after discount: ₱" + coffee1.calculatePrice("Small"));

        coffee1.addFlavor("Vanilla");
        coffee1.updateStock(5);
        coffee1.displayInfo();

        System.out.println();

        coffee2.displayInfo();
        System.out.println(coffee2.describe());

        coffee2.setDecaf(true);
        coffee2.changeRoastLevel("Light");
        coffee2.displayInfo();
    }
}
