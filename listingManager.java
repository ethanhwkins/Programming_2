package Coursework.Programming_2;

// Import necessary classes for functionality
import java.util.ArrayList;
import java.util.Scanner;

public class listingManager {

    // Array to store all available listings 
    private ArrayList<listing> listings = new ArrayList<>();
    listingFactory listingFactory = new listingFactory();

    // Method to create and add a new listing to the listings array
    public void createListing(Integer id, String userID, String title, String description, String location, Double price) {
        listing newListing = listingFactory.createListing(id, userID, title, description, location, price);
        listings.add(newListing);
    }
    
    // Method to retrieve all listings from the listings array
    public ArrayList<listing> getListings() {
        return listings;
    }

    // Method to validate price input from user to prevent errors
    public Double validatePrice (Scanner scanner) {
        double value = 0;
                    
        while (true) {
            System.out.print("Enter the price per month: £");
            String price = scanner.nextLine();
            try {
                value = Double.parseDouble(price);
                break; // Exit loop if parsing is successful

            } catch (Exception e) {
                System.out.println("Invalid price. Please enter only a numeric value such as '100'.");
            }
        }
        return value;
    }

    // Method to retrieve a listing by its ID
    public listing getListingById(Integer id) {
        for (int i = 0; i < listings.size(); i++) {
            listing listing = listings.get(i);
            if (listing.getListingID().equals(id)) {
                return listing;
            }
        }
        return null; // Return null if no listing is found with the given ID
    }

    // Method to delete all listings associated with a specific userID
    public void deleteUserListing(String userID) {
        listings.removeIf(listing -> listing.getUserID().equals(userID));
    }
}