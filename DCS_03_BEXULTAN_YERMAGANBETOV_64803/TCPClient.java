import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            BufferedReader configReader = new BufferedReader(new FileReader("config.txt"));
            String ip = configReader.readLine().split("=")[1].trim();
            int port = Integer.parseInt(configReader.readLine().split("=")[1].trim());
            configReader.close();

            Socket socket = new Socket(ip, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to the server at " + ip + ":" + port);
            String userCommand;
            while (true) {
                System.out.print("Enter command: ");
                userCommand = userInput.readLine();
                if (userCommand.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(userCommand);
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
