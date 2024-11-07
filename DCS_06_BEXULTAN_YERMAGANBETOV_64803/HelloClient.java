import java.rmi.*;
import java.util.List;

public class HelloClient {
    public static void main(String[] args) {
        try {
            HelloInterface hello = (HelloInterface) Naming.lookup("//localhost/Hello");

            // Example operations
            User newUser = new User("John", "Doe", "1990-01-01", 50000, User.Gender.MALE, "IT", "Developer");
            hello.addUser(newUser);

            User userDetails = hello.getUserDetails("John");
            System.out.println("Retrieved User: " + userDetails);

            hello.modifyUser("John",
                    new User("John", "Smith", "1990-01-01", 60000, User.Gender.MALE, "IT", "Senior Developer"));
            System.out.println("Modified User: " + hello.getUserDetails("John"));

            List<User> allUsers = hello.getAllUsers();
            System.out.println("All Users:");
            for (User user : allUsers) {
                System.out.println(user);
            }

            hello.deleteUser("John");
            System.out.println("User 'John' deleted.");

            hello.shutdown();
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
