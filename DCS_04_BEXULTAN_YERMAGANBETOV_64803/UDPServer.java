import java.net.*;
import java.util.HashMap;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(9876)) { // Use a suitable port number
            System.out.println("Server is running and waiting for clients...");

            HashMap<InetAddress, Integer> clientPorts = new HashMap<>();

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from client: " + clientAddress + ":" + clientPort + " - " + message);

                // Register client if not already present
                clientPorts.putIfAbsent(clientAddress, clientPort);

                if (message.equalsIgnoreCase("END")) {
                    System.out.println("Terminating communication with client " + clientAddress + ":" + clientPort);
                    clientPorts.remove(clientAddress);
                    continue;
                }

                // Relay message to the other client(s)
                for (InetAddress client : clientPorts.keySet()) {
                    if (!client.equals(clientAddress)) {
                        sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client,
                                clientPorts.get(client));
                        serverSocket.send(sendPacket);
                        System.out.println("Message sent to client: " + client + ":" + clientPorts.get(client));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
