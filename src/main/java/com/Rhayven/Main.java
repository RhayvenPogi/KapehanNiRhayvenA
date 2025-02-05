package com.Rhayven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final String KOPISHAP = "ResiboNgKapehan";

    public static void main(String[] args) {

        double[][] coffeeData = {
                {50.00, 0}, // Espresso
                {70.00, 0}, // Latte
                {65.00, 0}, // Cappuccino
                {80.00, 0}  // Mocha
        };
        String[] options = {"Espresso", "Latte", "Cappuccino", "Mocha"};
        int order = 0;

        while (true) {
            System.out.printf("""
                --- Coffee Menu ---
                1. Espresso - 50.00 PHP
                2. Latte - 70.00 PHP
                3. Cappuccino - 65.00 PHP
                4. Mocha - 80.00 PHP
                0. Finish Order
                """);

            int choice = getNumber("Choose your coffee (1-4, or 0 to finish): ", 0, 4);
            if (choice == 0) {
                break;
            }

            int quantity = noLimits("Enter quantity: ");
            coffeeData[choice - 1][1] += quantity;
            order++;
        }

        double subtotal = 0.0;
        StringBuilder receipt = new StringBuilder();
        receipt.append("---- Coffee Order Receipt ----\n");

        for (int i = 0; i < coffeeData.length; i++) {
            double prices = coffeeData[i][0];
            double quantity = coffeeData[i][1];
            if (quantity > 0) {
                double itemTotal = quantity * prices;
                subtotal += itemTotal;
                receipt.append(String.format("%-10s%-10s%-10s%-10s\n", "ORDER", "ITEM", "QUANTITY", "SUBTOTAL"));
                receipt.append(String.format("%-10d%-10s%-10f%-10.2f\n", order, options[i], quantity, itemTotal));
            }
        }

        double VAT = subtotal * 0.12; // 12% VAT
        double grandTotal = subtotal + VAT;
        System.out.printf("Grand Total: %-10.2f\n", grandTotal);

        receipt.append("------------------------------\n");
        receipt.append(String.format("Subtotal: %-10.2f\n", subtotal));
        receipt.append(String.format("VAT (12%%): %-10.2f\n", VAT));
        receipt.append(String.format("Grand Total: %-10.2f\n", grandTotal));

        Scanner pogi = new Scanner(System.in);
        double bayad = 0.0;
        System.out.print("Enter your payment: ");
        // Use try-catch block to handle exceptions
        try {
            bayad = Double.parseDouble(pogi.nextLine());  // Read and parse the input as a double
            System.out.println("Your payment is: " + bayad);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }

        if (bayad >= grandTotal){
            receipt.append(String.format("Payment: %-10.2f\n", bayad));
            double sukli = bayad - grandTotal;
            receipt.append(String.format("Change: %-10.2f", sukli));

            System.out.println(receipt.toString());
            System.out.println();
            // Generate a random file name
            int fileName = (int) (Math.random() * 8999) + 1000; // Ensure a 4-digit number
            saveToFile(fileName + "_KapehanResibo.txt", receipt.toString());
        } else{
            System.out.println("Your payment is less than the Grand Total");
        }
    }

    public static int getNumber(String prompt, int min, int max) {
        Scanner pogi = new Scanner(System.in);
        int number;
        while (true) {
            System.out.print(prompt);
            if (pogi.hasNextInt()) {
                number = pogi.nextInt();
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                pogi.nextLine(); // Clear invalid input
            }
        }
    }

    public static int noLimits(String prompt) {
        Scanner pogi = new Scanner(System.in);
        int number;
        while (true) {
            System.out.print(prompt);
            if (pogi.hasNextInt()) {
                number = pogi.nextInt();
                if (number >= 0) {
                    return number; // Accept any non-negative quantity
                } else {
                    System.out.println("Invalid input. Please enter a non-negative number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                pogi.nextLine(); // Clear invalid input
            }
        }
    }

    public static void saveToFile(String fileName, String receipt) {
        File folder = new File(KOPISHAP);
        if (!folder.exists()) {
            folder.mkdirs(); // Create the directory if it doesn't exist
        }
        File receiptFile = new File(folder, fileName);
        try (FileWriter fileWriter = new FileWriter(receiptFile)) {
            fileWriter.write(receipt);
            System.out.println("Receipt saved to ResiboNgKapehan.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
