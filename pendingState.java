package Coursework.Programming_2;

public class pendingState implements State {

    // Booking reference
    booking booking;

    // Define permitted state transitions from pending state
    @Override
    public void pending(booking booking, String currentUser) {
        System.out.println("Booking is already pending. Cannot move to pending state again.");
    }

    @Override
    public void accepted(booking booking, String currentUser) {
        booking.setState(new acceptedState(booking));
        System.out.println("Booking has now been accepted.");
    }

    @Override
    public void rejected(booking booking, String currentUser) {
        booking.setState(new rejectedState(booking));
        System.out.println("Booking has now been rejected.");
    }
}