package com.Rhayven;

public class Main {
    public static void main(String[] args) {
        String[] espressoFlavors = {"Chocolate", "Nutty"};
        String[] latteFlavors = {"Creamy", "Caramel"};

        Coffee coffee1 = new Coffee("Espresso", "Arabica", "Small", 50.00, "Dark", "Colombia", false, 10, espressoFlavors, "Espresso");
        Coffee coffee2 = new Coffee("Latte", "Robusta", "Medium", 70.00, "Medium", "Brazil", false, 5, latteFlavors, "Drip");

        coffee1.displayInfo();
        System.out.println(coffee1.describe());
        System.out.println();

        coffee1.discount(10);
        System.out.println("Price after discount: ₱" + coffee1.calculatePrice("Small"));
        System.out.println();

        coffee1.addFlavor("Vanilla");
        coffee1.setDecaf(false);
        coffee2.changeRoastLevel("Light");
        coffee1.updateStock(-5);
        coffee1.displayInfo();

        System.out.println();

        coffee2.displayInfo();
        System.out.println(coffee2.describe());
        System.out.println();

        coffee2.discount(10);
        System.out.println("Price after discount: ₱" + coffee2.calculatePrice("Medium"));
        System.out.println();

        coffee2.addFlavor("Cinnamon");
        coffee2.setDecaf(true);
        coffee2.changeRoastLevel("Italian");
        coffee2.updateStock(-2);
        coffee2.displayInfo();
    }
}
