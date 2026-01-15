package Coursework.Programming_2;

public class booking {

    // State attribute to hold the current state of the booking
    private State state;

    // Booking attributes
    private Integer bookingID;
    private String senderID;
    private String recipientID;
    private Integer listingID;

    // Constructor to initialize booking attributes and set initial state
    public booking(Integer bookingID, String senderID, String recipientID, Integer listingID) {
        this.bookingID = bookingID;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.listingID = listingID;

        // Set initial state of every booking that is created to pending by default
        this.state = new pendingState();
    }

    // Delegate behaviour to the current state
    public void accept(String currentUser) {
        state.accepted(this, currentUser);
    }

    public void reject(String currentUser) {
        state.rejected(this, currentUser);
    }

    // State management
    public void setState(State newState) {
        this.state = newState;
    }

    public State getState() {
        return state;
    }

    // Getters and Setters
    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public Integer getListingID() {
        return listingID;
    }

    public void setListingID(Integer listingID) {
        this.listingID = listingID;
    }

    // \override toString method so that booking details can be printed easily
    @Override
    public String toString() {
        return
                "BookingID: " + bookingID + "\n" +
                "SenderID: " + senderID + "\n" +
                "RecipientID: " + recipientID + "\n" +
                "ListingID: " + listingID + "\n" +
                "Status: " + state.getClass().getSimpleName() + "\n";
    }
}