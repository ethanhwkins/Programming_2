package Coursework.Programming_2;

// Import necessary classes for functionality
import java.util.HashMap;

public class userManager {
    private HashMap<String, user> registeredUsers = new HashMap<>();  // Store active users with username as key
    private userFactory userFactory = new userFactory();  // Instance of userFactory to create user objects
    private listingManager listingManager; // Instance of listingManager to delete a user's listings
    private bookingManager bookingManager; // Instance of bookingManager to delete a user's bookings

    // Constructor to initialize userManager with a listingManager instance from the main class
    public userManager(listingManager listingManager2, bookingManager bookingManager2) {
        this.listingManager = listingManager2;
        this.bookingManager = bookingManager2;
    }

    // Method to add a new user to the active users list
    public void addUser(String username, String password, Integer accountType) {

        // Credential validation checking before account creation

        // Check if username already exists
        if (registeredUsers.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username." + "\n");
            return;
        }

        // Check for empty username or password fields
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty. Please try again." + "\n");
            return;
        }

        // Check if password meets minimum length requirement
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long. Please try again." + "\n");
            return;
        }

        // Create new user using userFactory and add to registeredUsers map
        user newUser = userFactory.createUser(username, password, accountType);
        registeredUsers.put(username, newUser);

        // Confirmation message for successful account creation
        System.out.println("Account created successfully. You can now log in." + "\n");
    }

    // Method to retrieve a user by username from the active users list
    public user getUser(String username) {
        return registeredUsers.get(username);
    }

    // Method to delete a user and their associated listings
    public void deleteUser(String username) {
        listingManager.deleteUserListing(username);
        bookingManager.deleteUserBookings(username);
        if (registeredUsers.containsKey(username)) {
            registeredUsers.remove(username);
        }
    }
}