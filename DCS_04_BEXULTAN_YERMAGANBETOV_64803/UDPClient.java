import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Replace with server IP if necessary
            int serverPort = 9876; // Use the same port as the server

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            System.out.println("Connected to server. Type your messages:");
            while (true) {
                System.out.print("You: ");
                String message = userInput.readLine();

                // Send data to server
                sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                if (message.equalsIgnoreCase("END")) {
                    System.out.println("Connection closed.");
                    break;
                }

                // Receive response from server
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
