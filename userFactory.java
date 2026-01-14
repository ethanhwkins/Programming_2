package Coursework.Programming_2;

public class userFactory {

    // Creational design pattern to create user objects
    public user createUser(String username, String password, Integer accountType) {
        user user = new user();
        user.setUsername(username);
        user.setPassword(password);
        user.setAccountType(accountType);
        return user;
    }
}