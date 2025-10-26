import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;//des define
import java.util.Base64;
import java.util.Scanner;

public class Des {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter 8-character key: ");
            String keyInput = sc.nextLine();
            if (keyInput.length() != 8) {
                System.out.println("Key must be exactly 8 characters long!");
                return;
            }

            SecretKey secretKey = new SecretKeySpec(keyInput.getBytes(), "DES");//creates a secretKeySpec object for des using that key.

            Cipher cipher = Cipher.getInstance("DES");

            System.out.print("Enter text to encrypt: ");
            String plainText = sc.nextLine();

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);//initializes it in encryption mode with the key.
            byte[] encrypted = cipher.doFinal(plainText.getBytes());//encrypts the plaintext into bytes.
            String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted);//converts encrypted bytes into a readable Base64 string.
            System.out.println("Encrypted text : " + encryptedBase64);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);//initializes it in decryption mode with the key.
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));//decrypts the encrypted bytes into plaintext .
            System.out.println("Decrypted text: " + new String(decrypted));

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
