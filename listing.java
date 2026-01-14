package Coursework.Programming_2;

public class listing {

    // Listing attributes
    private Integer ListingID;
    private String userID;
    private String Title;
    private String Description;
    private String Location;
    private Double Price;

    // Getters and Setters
    public Integer getListingID() {
        return ListingID;
    } 

    public void setListingID(Integer listingID) {
        ListingID = listingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    // Override toString method so that listing details can be printed easily in the main class for the user to read
    @Override
    public String toString() {
        return 
                "Listing ID: " + ListingID + "\n" +
                "User ID: " + userID + "\n" +
                "Title: " + Title + "\n" +
                "Description: " + Description + "\n" +
                "Location: " + Location + "\n" +
                "Price: £" + Price + "\n";
    }
}
