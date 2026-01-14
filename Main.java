package Coursework.Programming_2;

// Import necessary classes for application functionality
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // main method to run the application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Initialize scanner for user input
        boolean authenticated = false;  // Track login status in order to control access to menus
        boolean running = true;   // Control the main application loop
        String currentUser = "";  // Track the currently logged-in user
        Integer accessLevel = null;  // Track the account type of the current user

        // Instantiate necessary classes for application functionality
        listingManager listingManager = new listingManager();
        bookingManager bookingManager = new bookingManager();
        userManager userManager = new userManager(listingManager, bookingManager);  // Pass listingManager to userManager class, this is necessary for deleting user listings upon account deletion
        authenticate authenticate = new authenticate(userManager);  // Pass userManager to authenticate class, this is necessary for validating created user credentials

        // Main application loop
        while(running == true) {
            // Loop to handle user login or signup, it is dependent on the isLoggedIn boolean variable being false
            while (authenticated == false) {
            Coursework.Programming_2.menu.displaySignupMenu();
            int option1 = scanner.nextInt();        
            scanner.nextLine();  // Consume newline so buffer is clear for next input
            
                // Switch case to handle login or signup based on user input
                switch (option1) {
                    case 1:

                        // Loop to handle user login until successful authentication
                        while (authenticated == false) {
                            System.out.print("Enter username: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String password = scanner.nextLine();

                            // Validate credentials using authenticate class, the method returns true or false depending on validity
                            if (authenticate.validateCredentials(username, password) == true) {
                                authenticated = true;
                                currentUser = username; // Set the current user upon successful login
                                accessLevel = userManager.getUser(username).getAccountType(); // Retrieve and set the account type of the current user
                                System.out.println("Login successful." + "\n");
                            } 
                            else {
                                System.out.println("Invalid credentials. Please try again.");
                            }
                            break;
                        }
                        break;

                    // Switch case to handle user signup 
                    case 2:

                        // Ask user for credentials to create a new account
                        System.out.print("Create a username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Create a password: ");
                        String newPassword = scanner.nextLine();
                        System.out.print("Enter account type (tenant = 1 / homeowner = 2): ");
                        Integer accountType = scanner.nextInt();
                        scanner.nextLine();  // Consume newline so buffer is clear for next input

                        // Add new user to userManager using addUser method, which utilizes userFactory to create the user object
                        userManager.addUser(newUsername, newPassword, accountType);

                    default:
                        System.out.println("Invalid option. Please try again." + "\n");
                }
            }

                // Main menu loop after successful login to handle user actions, it is dependent on the isLoggedIn boolean variable being true
                while (authenticated == true) {
                    System.out.println("Hello, " + currentUser + "!" + "\n"); // Welcome message
                    Coursework.Programming_2.menu.displayMainMenu();  // Statically call display main menu options
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline so buffer is clear for next input

                    // Switch case to handle main menu options based on user input
                    switch (choice) {
                        // case 1 displays available properties by retrieving listings from listingManager and providing the user the option to make a booking if they are a tenant
                        case 1:

                            // Ensures that only tenants can make bookings
                            if (accessLevel == 1) { 
                                System.out.println("You have selected to view available properties." + "\n");

                                // Returns user to main menu if no listings are available
                                ArrayList<listing> listings = listingManager.getListings();
                                    if (listings.isEmpty()) {
                                    System.out.println("No properties are currently available." + "\n");

                                    break;
                                    } 
                                
                                // Displays all available listings
                                else {
                                    for (int i = 0; i < listings.size(); i++) {
                                        System.out.println(listings.get(i));
                                    }
                                }
                                    // Prompt user to enter a listing ID to make a booking or return to the main menu
                                    System.out.print("Enter the Listing ID to make a booking, or 0 to return to the main menu: ");
                                    int listingID = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    // Process booking if a valid listing ID is provided
                                    if (listingID != 0) {
                                        listing selectedListing = listingManager.getListingById(listingID);
                                        if (selectedListing != null) {
                                            // Create a new booking using bookingManager with the current user as the senderID and the listing owner as the recipientID
                                            int bookingID = bookingManager.getBookings().size() + 1; // Generate a new booking ID
                                            bookingManager.createBooking(bookingID, currentUser, selectedListing.getUserID(), listingID);
                                            System.out.println("Booking request sent successfully for Listing ID: " + listingID + "\n");
                                        } 
                                        
                                        else {
                                            System.out.println("Invalid Listing ID. Please try again." + "\n");
                                        }
                                    }
                                } 
                                
                            else {
                                System.out.println("Only tenants can view and book available properties." + "\n");
                            }
                            break;

                        // Case 2 allows a homeowner to create a new property listing by providing necessary details 
                        case 2:
                            
                            // Ensures that only homeowners can create listings
                            if (accessLevel == 2) { 
                                System.out.println("You have selected to create a listing." + "\n");

                                // Generate a new ID for the listing based on the current number of listings
                                int ID = listingManager.getListings().size() + 1;

                                // Collect listing details from user input
                                System.out.print("Enter title: ");
                                String title = scanner.nextLine();

                                System.out.print("Enter description: ");
                                String description = scanner.nextLine();

                                System.out.print("Enter location: ");
                                String location = scanner.nextLine();

                                Double value = listingManager.validatePrice(scanner);

                                // Create the new listing using listingManager with the current user being passed as the userID
                                listingManager.createListing(ID, currentUser, title, description, location, value);
                                System.out.println("Listing created successfully with ID: " + ID + "\n");
                            } 
                            else {
                                System.out.println("Only homeowners can create listings." + "\n");
                            }
                            break;

                        // Case 3 allows the user to view their notifications, displaying all bookings made and whether they are accepted, declined or pending for a tenant, and allowing homeowners to accept or reject booking requests
                        case 3:

                            System.out.println("You have selected to view your bookings." + "\n");

                            // Returns user to main menu if there are no bookings
                            ArrayList<booking> bookings = bookingManager.getBookings();
                            if (bookings.isEmpty()) {
                                System.out.println("Your bookings list is empty." + "\n");

                                break;
                            } 
                            
                            // Displays all bookings associated with the current user
                            else {
                                for (int i = 0; i < bookings.size(); i++) {
                                    System.out.println(bookings.get(i));
                                }
                            }

                            // Ensures that only homeowners can update booking states
                            if (accessLevel == 2) { 

                                System.out.println("Enter the Booking ID to update, or 0 to return to the main menu: ");
                                int bookingID = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                if (bookingID != 0) {

                                    System.out.println("Enter 1 to accept or 2 to reject the booking: ");
                                    Integer action = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    if (action == 1) {
                                        bookingManager.acceptBooking(bookingID, currentUser);
                                    } 
                                    
                                    else if (action == 2) {
                                        bookingManager.rejectBooking(bookingID, currentUser);
                                    } 
                                
                                    else {
                                        System.out.println("Invalid action. Please enter 'accept' or 'reject'.");
                                    }
                                }
                            }
                            break;

                        // Case 4 allows the user to delete their account, removing their user data and any associated listings and bookings
                        case 4:
                            userManager.deleteUser(currentUser);
                            authenticated = false; // Set authenticated to false to exit the main menu loop
                            System.out.println("Your account has been deleted. Returning to the sign-in menu...\n");
                            break;

                        // Case 5 logs the user out, returning them to the login/signup menu
                        case 5:
                            authenticated = false; // Set authenticated to false to exit the main menu loop
                            System.out.println("You have been logged out. Returning to the sign-in menu...\n");
                            break;

                        // Default case to handle invalid menu options
                        default:
                            System.out.println("Invalid option. Please try again." + "\n");
                    }
                } 
            }
        scanner.close();
    } 
}