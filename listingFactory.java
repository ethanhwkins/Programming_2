package Coursework.Programming_2;

public class listingFactory {
    
    // Creational design pattern to create listing objects
    public listing createListing(Integer listingID, String userID, String title, String description, String location, Double price) {
        listing listing = new listing();
        listing.setListingID(listingID);
        listing.setUserID(userID);
        listing.setTitle(title);
        listing.setDescription(description);
        listing.setLocation(location);
        listing.setPrice(price);
        return listing;
    }  
}