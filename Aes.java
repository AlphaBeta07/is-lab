import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;//aes define
import java.util.Base64;
import java.util.Scanner;

public class Aes {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("AES practical ASL");
            System.out.print("Enter a 16-character key: ");
            String key = sc.nextLine();

            if (key.length() != 16) {
                System.out.println("Key must be exactly 16 characters!");
                return;
            }

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");//creates a SecretKeySpec object for AES using that key.

            System.out.print("Enter text to encrypt: ");
            String plainText = sc.nextLine();

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted Text: " + encryptedText);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            String decryptedText = new String(decryptedBytes);

            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
