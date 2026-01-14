package Coursework.Programming_2;

public interface State {

    // State design pattern methods
    void pending(booking booking, String currentUser);
    void accepted(booking booking, String currentUser);
    void rejected(booking booking, String currentUser);
}