import java.math.BigInteger;
import java.util.Scanner;

public class Rsa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigInteger p = BigInteger.valueOf(61);
        BigInteger q = BigInteger.valueOf(53);
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(17); // public exponent
        BigInteger d = e.modInverse(phi);      // private exponent

        System.out.println("Public Key (e,n): (" + e + "," + n + ")");
        System.out.println("Private Key (d,n): (" + d + "," + n + ")");

        System.out.print("\nEnter your message: ");
        String message = sc.nextLine();

        BigInteger[] cipher = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            int ascii = (int) message.charAt(i);
            cipher[i] = BigInteger.valueOf(ascii).modPow(e, n);
        }

        System.out.print("Encrypted message: ");
        for (BigInteger c : cipher) {
            System.out.print(c + " ");
        }
        System.out.println();

        StringBuilder decrypted = new StringBuilder();
        for (BigInteger c : cipher) {
            int ascii = c.modPow(d, n).intValue();
            decrypted.append((char) ascii);
        }

        System.out.println("Decrypted message: " + decrypted.toString());
        sc.close();
    }
}
