import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a port number as an argument.");
            return;
        }

        int port = Integer.parseInt(args[0]);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(
                    "Server started at IP: " + InetAddress.getLocalHost().getHostAddress() + ", Port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String command = inputLine.split(" ")[0];
                    String text = inputLine.substring(inputLine.indexOf(" ") + 1);

                    switch (command.toUpperCase()) {
                        case "STATS":
                            int lowercase = (int) text.chars().filter(Character::isLowerCase).count();
                            int uppercase = (int) text.chars().filter(Character::isUpperCase).count();
                            int digits = (int) text.chars().filter(Character::isDigit).count();
                            int others = text.length() - (lowercase + uppercase + digits);
                            out.println("Lowercase: " + lowercase + ", Uppercase: " + uppercase + ", Digits: " + digits
                                    + ", Others: " + others);
                            break;
                        case "ANAGRAM":
                            out.println("Anagram: " + new StringBuilder(text).reverse().toString());
                            break;
                        case "DROP":
                            out.println("Connection closed.");
                            clientSocket.close();
                            break;
                        default:
                            out.println("Error: Invalid command.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
