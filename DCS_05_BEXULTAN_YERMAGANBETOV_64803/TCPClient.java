import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 12345); // Replace "localhost" with server IP if necessary
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Connected to the server. Type 'exit' to close the connection.");
            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading server message: " + e.getMessage());
                }
            }).start();

            String clientInput;
            while ((clientInput = userInput.readLine()) != null) {
                out.println(clientInput);
                if (clientInput.equalsIgnoreCase("exit")) {
                    System.out.println("Connection closed.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
