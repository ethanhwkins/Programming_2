package Coursework.Programming_2;

public class rejectedState implements State {

    // Booking reference
    booking booking;

    // Constructor for rejected state
    public rejectedState(booking booking) {
        this.booking = booking;
    }

    // Define permitted state transitions from rejected state
    @Override
    public void pending(booking booking, String currentUser) {
        System.out.println("Booking is already rejected. Cannot move to pending state.");
    }

    @Override
    public void accepted(booking booking, String currentUser) {
        System.out.println("Booking is already rejected. Cannot move to accepted state.");
    }

    @Override
    public void rejected(booking booking, String currentUser) {
        System.out.println("Booking is already rejected. Cannot move to rejected state again.");
    }
}