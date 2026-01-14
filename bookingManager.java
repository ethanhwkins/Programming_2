package Coursework.Programming_2;

import java.util.ArrayList;

public class bookingManager {

    // Array to store all bookings
    private ArrayList<booking> bookings = new ArrayList<>();

    // Instance of bookingFactory to create booking objects
    private bookingFactory bookingFactory = new bookingFactory();

    // Method to create and add a new booking
    public void createBooking(Integer bookingID, String senderID, String recipientID, Integer listingID) {
        booking newBooking = bookingFactory.createBooking(bookingID, senderID, recipientID, listingID);
        bookings.add(newBooking);
    }

    // Method to retrieve all bookings
    public ArrayList<booking> getBookings() {
        return bookings;
    }

    // Method to retrieve bookings by sender ID
    public ArrayList<booking> getBookingsByID(String senderID) {
        if (senderID == null) {
            return new ArrayList<>();
        }
        ArrayList<booking> userBookings = new ArrayList<>();
        for (booking book : bookings) {
            if (book.getSenderID().equals(senderID) || book.getRecipientID().equals(senderID)) {
                userBookings.add(book);
            }
        }
        return userBookings;
    }

    public String getSenderID(Integer bookingID) {
        for (booking book : bookings) {
            if (book.getBookingID().equals(bookingID)) {
                return book.getSenderID();
            }
        }
        return null;
    }

    // Method to accept a booking by its ID
    public void acceptBooking(Integer bookingID, String currentUser) {
        booking booking = getBookingByID(bookingID);
        if (booking != null) {
            booking.accept(currentUser);
        }
    }

    // Method to reject a booking by its ID
    public void rejectBooking(Integer bookingID, String currentUser) {
        booking booking = getBookingByID(bookingID);
        if (booking != null) {
            booking.reject(currentUser);
        }
    }

    public booking getBookingByID(Integer bookingID) {
        for (booking book : bookings) {
            if (book.getBookingID().equals(bookingID)) {
                return book;
            }
        }
        return null;
    }

    public void deleteUserBookings(String userID) {
        bookings.removeIf(booking -> booking.getSenderID().equals(userID) || booking.getRecipientID().equals(userID));
    }
}   