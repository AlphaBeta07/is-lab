import java.util.Scanner;

public class Vigenere {

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                int textChar = c - 'A';
                int keyChar = key.charAt(i % key.length()) - 'A';
                char encryptedChar = (char) ((textChar + keyChar) % 26 + 'A');
                result.append(encryptedChar);
            } else {
                result.append(c); 
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                int textChar = c - 'A';
                int keyChar = key.charAt(i % key.length()) - 'A';
                char decryptedChar = (char) ((textChar - keyChar + 26) % 26 + 'A');
                result.append(decryptedChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}
