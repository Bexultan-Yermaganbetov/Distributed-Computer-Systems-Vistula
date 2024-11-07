import java.io.*;
import java.net.*;

public class ServerThread implements Runnable {
    private Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            String clientMessage;
            System.out.println("Ready to chat with client " + clientSocket.getInetAddress().getHostAddress());
            new Thread(() -> {
                try {
                    String serverInput;
                    while ((serverInput = userInput.readLine()) != null) {
                        out.println("Server: " + serverInput);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading server input: " + e.getMessage());
                }
            }).start();

            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Client: " + clientMessage);
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error in server thread: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
