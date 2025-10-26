import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigInteger p = BigInteger.valueOf(23); // prime number
        BigInteger g = BigInteger.valueOf(5);  // primitive root modulo p

        System.out.println("Prime number (p): " + p);
        System.out.println("Primitive root (g): " + g);

        System.out.print("\nAlice, enter private key (a): ");
        BigInteger a = BigInteger.valueOf(sc.nextInt());

        System.out.print("Bob, enter private key (b): ");
        BigInteger b = BigInteger.valueOf(sc.nextInt());

        BigInteger A = g.modPow(a, p); // Alice's public value
        BigInteger B = g.modPow(b, p); // Bob's public value

        System.out.println("\nAlice sends A = g^a mod p = " + A + " to Bob");
        System.out.println("Bob sends B = g^b mod p = " + B + " to Alice");

        BigInteger sharedSecretAlice = B.modPow(a, p);
        BigInteger sharedSecretBob = A.modPow(b, p);

        System.out.println("\nAlice computes shared secret: K = B^a mod p = " + sharedSecretAlice);
        System.out.println("Bob computes shared secret: K = A^b mod p = " + sharedSecretBob);

        if (sharedSecretAlice.equals(sharedSecretBob)) {
            System.out.println("\nShared secret established successfully!");
        } else {
            System.out.println("\nError: Shared secrets do not match!");
        }

        sc.close();
    }
}
