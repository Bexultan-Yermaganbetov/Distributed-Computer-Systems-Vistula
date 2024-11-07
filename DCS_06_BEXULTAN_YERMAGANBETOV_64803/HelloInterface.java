import java.rmi.*;
import java.util.List;

public interface HelloInterface extends Remote {
    void addUser(User user) throws RemoteException;

    void deleteUser(String firstName) throws RemoteException;

    User getUserDetails(String firstName) throws RemoteException;

    List<User> getAllUsers() throws RemoteException;

    void modifyUser(String firstName, User updatedUser) throws RemoteException;

    void shutdown() throws RemoteException;
}
