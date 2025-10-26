import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class ShaOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter secret key: ");
        String key = sc.nextLine();

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        try {
            String hmac = generateHMAC(message, key);
            System.out.println("Generated HMAC (SHA-1): " + hmac);

            // Verify HMAC
            System.out.print("\nRe-enter message to verify: ");
            String msg2 = sc.nextLine();

            System.out.print("Re-enter secret key: ");
            String key2 = sc.nextLine();

            String hmac2 = generateHMAC(msg2, key2);

            if (hmac.equals(hmac2)) {
                System.out.println("HMAC Verified! Message is intact.");
            } else {
                System.out.println("HMAC Verification Failed! Message may have been altered.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // Function to generate HMAC-SHA1 and return as hex string
    public static String generateHMAC(String message, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1"); // Use SHA-1
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        mac.init(secretKey);

        byte[] hmacBytes = mac.doFinal(message.getBytes());
        return bytesToHex(hmacBytes);
    }

    // Convert byte array to hex string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
