package Coursework.Programming_2;

public class acceptedState implements State {

    // Booking reference
    booking booking;

    // Constructor for accepted state
    public acceptedState(booking booking) {
        this.booking = booking;
    }

    // Allowed state transitions from accepted state
    @Override
    public void pending(booking booking, String currentUser) {
        System.out.println("Booking is already accepted. Cannot move to pending state.");
    }

    @Override
    public void accepted(booking booking, String currentUser) {
        System.out.println("Booking is already in accepted state. Cannot move to accepted state again.");
    }

    @Override
    public void rejected(booking booking, String currentUser) {
        System.out.println("Booking is already accepted. Cannot move to rejected state.");
    }
}