package Coursework.Programming_2;

public class authenticate {
    private userManager userManager; // Instance of userManager to access user data

    // Constructor to initialize authenticate with a userManager instance from the main class
    public authenticate(userManager userManager2) {
        this.userManager = userManager2;
    }

    // Method to validate user credentials
    public boolean validateCredentials(String username, String password) {
        user user = userManager.getUser(username); // Retrieve user object using userManager

        // Check if user exists and if the provided password matches the stored password, return true if valid, or false if invalid
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}