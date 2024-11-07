import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public class Hello extends UnicastRemoteObject implements HelloInterface {
    private List<User> users;

    protected Hello() throws RemoteException {
        users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) throws RemoteException {
        users.add(user);
        System.out.println("User added: " + user);
    }

    @Override
    public void deleteUser(String firstName) throws RemoteException {
        users.removeIf(user -> user.getFirstName().equalsIgnoreCase(firstName));
        System.out.println("User deleted: " + firstName);
    }

    @Override
    public User getUserDetails(String firstName) throws RemoteException {
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(firstName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws RemoteException {
        return users;
    }

    @Override
    public void modifyUser(String firstName, User updatedUser) throws RemoteException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                users.set(i, updatedUser);
                System.out.println("User modified: " + updatedUser);
                break;
            }
        }
    }

    @Override
    public void shutdown() throws RemoteException {
        System.out.println("Shutting down the server...");
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            HelloInterface hello = new Hello();
            Naming.rebind("Hello", hello);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
