import java.net.InetAddress;

public class FirstNet {
    public static void main(String[] args) {
        try {
            // Check if an argument is provided
            if (args.length == 0) {
                System.out.println("Please provide a hostname or IP address.");
                return;
            }

            // Get the host from the argument
            String host = args[0];
            InetAddress inetAddress = InetAddress.getByName(host);

            // Print basic information about the host
            System.out.println("Connecting to: " + inetAddress.getHostName());
            System.out.println("Is reachable: " + inetAddress.isReachable(5000));
            System.out.println("Canonical Host Name: " + inetAddress.getCanonicalHostName());
            System.out.println("Hash Code: " + inetAddress.hashCode());
            System.out.println("Is Multicast Address: " + inetAddress.isMulticastAddress());

            // Connect using IP address
            byte[] ip = inetAddress.getAddress();
            InetAddress inetByIp = InetAddress.getByAddress(ip);
            System.out.println("Connected using IP: " + inetByIp.getHostName());

            // Check availability of IPs in a range (example range)
            System.out.println("Checking availability in range:");
            for (int i = 1; i <= 10; i++) { // Change range as needed
                byte[] ipRange = { (byte) 192, (byte) 168, 1, (byte) i }; // Adjust network as needed
                InetAddress address = InetAddress.getByAddress(ipRange);
                if (address.isReachable(1000)) {
                    System.out.println("Host " + address.getHostAddress() + " is reachable.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
