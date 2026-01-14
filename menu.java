package Coursework.Programming_2;

public class menu {

    // Display the main menu options to the user
    public static void displayMainMenu() {
        System.out.print("Please select an option to continue: " + "\n");
        System.out.println("1. View available properties");
        System.out.println("2. Create a listing");
        System.out.println("3. View inbox");
        System.err.println("4. Account settings");
        System.out.println("5. Logout");
    
    }

    // Display the signup menu options to the user
    public static void displaySignupMenu() {
        System.out.println("Welcome to the Find My Accommodation Application! Select an option to continue" + "\n");
        System.out.println("1. Sign in as existing user");
        System.out.println("2. Create a new user account");
    }
}