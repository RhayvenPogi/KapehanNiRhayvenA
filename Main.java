package com.Rhayven;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main{
    static final String KOPISHAP = "ResiboNgKapehan";

    public static void main(String[] args) {
        float[] prices = {50.00f, 70.00f, 65.00f, 80.00f};
        String[] options = {"Espresso", "Latte", "Cappuccino", "Mocha"};
        int[] quantities = new int[4];

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
            quantities[choice - 1] += quantity;
        }

        float subtotal = 0.0f;
        String receipt = "---- Coffee Order Receipt ----\n";

        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] > 0) {
                float itemTotal = quantities[i] * prices[i];
                subtotal += itemTotal;
                receipt += String.format("%d x %s @ %.2f each = %.2f\n", quantities[i], options[i], prices[i], itemTotal);
            }
        }
        System.out.println();
        float VAT = subtotal * 0.12f; // 12% VAT
        float grandTotal = subtotal + VAT;

        receipt += "------------------------------\n";
        receipt += String.format("Subtotal: %.2f\n", subtotal);
        receipt += String.format("VAT (12%%): %.2f\n", VAT);
        receipt += String.format("Grand Total: %.2f\n", grandTotal);

        System.out.println(receipt);

        // Generate a random file name
        int fileName = (int) (Math.random() * 8999) + 1000; // Ensure a 4-digit number
        saveToFile(fileName + "_KapehanResibo.txt", receipt);
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



