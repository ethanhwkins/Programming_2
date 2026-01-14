package Coursework.Programming_2;

public class bookingFactory {

    // Creational design pattern to create booking objects
    public booking createBooking(Integer bookingID, String senderID, String recipientID, Integer listingID) {

        return new booking(bookingID, senderID, recipientID, listingID);
    }
}