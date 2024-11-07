import java.net.NetworkInterface;
import java.net.InterfaceAddress;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Interfaces {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Usage:");
                System.out.println("1. java Interfaces <interface_name> - Show details of the selected network interface");
                System.out.println("2. java Interfaces all - List all non-virtual network interfaces");
                return;
            }

            if (args[0].equalsIgnoreCase("all")) {
                // Task 2: List all non-virtual network interfaces
                for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (!networkInterface.isVirtual()) {
                        System.out.println("Interface Name: " + networkInterface.getName());
                        if (networkInterface.isLoopback()) {
                            System.out.println(" (loopback)");
                        }
                        System.out.println("MTU: " + networkInterface.getMTU());

                        List<InterfaceAddress> addresses = networkInterface.getInterfaceAddresses();
                        for (InterfaceAddress address : addresses) {
                            InetAddress inetAddress = address.getAddress();
                            if (inetAddress != null) {
                                System.out.println("IP Address: " + inetAddress.getHostAddress());
                            }
                            System.out.println("Subnet Mask Length: " + address.getNetworkPrefixLength());
                            InetAddress broadcast = address.getBroadcast();
                            if (broadcast != null) {
                                System.out.println("Broadcast Address: " + broadcast.getHostAddress());
                            }
                        }
                        System.out.println();
                    }
                }
            } else {
                // Task 1: Show details of a selected network interface
                String interfaceName = args[0];
                NetworkInterface networkInterface = NetworkInterface.getByName(interfaceName);

                if (networkInterface == null) {
                    System.out.println("Network interface " + interfaceName + " not found.");
                    return;
                }

                System.out.println("Interface Name: " + networkInterface.getName());
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    StringBuilder macAddress = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        macAddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    System.out.println("MAC Address: " + macAddress.toString());
                } else {
                    System.out.println("MAC Address: Not available");
                }

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println("IP Address: " + inetAddress.getHostAddress());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
