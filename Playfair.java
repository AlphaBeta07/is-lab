import java.util.*;
public class Playfair {
    static char[][] keyTable = new char[5][5];
    static Map<Character, int[]> pos = new HashMap<>();

    static void generateKey(String key) {
        key = key.toUpperCase().replace("J", "I");
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];

        for (char c : key.toCharArray()) {
            if (c < 'A' || c > 'Z') continue;
            if (!used[c - 'A']) {
                sb.append(c);
                used[c - 'A'] = true;
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!used[c - 'A']) {
                sb.append(c);
                used[c - 'A'] = true;
            }
        }

        int idx = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                char ch = sb.charAt(idx++);
                keyTable[r][c] = ch;
                pos.put(ch, new int[]{r, c});
            }
        }
    }

    static String process(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length() && text.charAt(i + 1) != a) ? text.charAt(++i) : 'X';
            sb.append(a).append(b);
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    static String encrypt(String text) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] pa = pos.get(a), pb = pos.get(b);
            if (pa[0] == pb[0]) {
                res.append(keyTable[pa[0]][(pa[1] + 1) % 5]);
                res.append(keyTable[pb[0]][(pb[1] + 1) % 5]);
            } else if (pa[1] == pb[1]) {
                res.append(keyTable[(pa[0] + 1) % 5][pa[1]]);
                res.append(keyTable[(pb[0] + 1) % 5][pb[1]]);
            } else {
                res.append(keyTable[pa[0]][pb[1]]);
                res.append(keyTable[pb[0]][pa[1]]);
            }
        }
        return res.toString();
    }

    static String decrypt(String text) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] pa = pos.get(a), pb = pos.get(b);
            if (pa[0] == pb[0]) {
                res.append(keyTable[pa[0]][(pa[1] + 4) % 5]);
                res.append(keyTable[pb[0]][(pb[1] + 4) % 5]);
            } else if (pa[1] == pb[1]) {
                res.append(keyTable[(pa[0] + 4) % 5][pa[1]]);
                res.append(keyTable[(pb[0] + 4) % 5][pb[1]]);
            } else {
                res.append(keyTable[pa[0]][pb[1]]);
                res.append(keyTable[pb[0]][pa[1]]);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String text = sc.nextLine();
        System.out.print("Enter keyword: ");
        String key = sc.nextLine();

        generateKey(key);
        String prepared = process(text);
        String enc = encrypt(prepared);
        String dec = decrypt(enc);

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}
