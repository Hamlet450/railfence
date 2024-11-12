import java.util.Scanner;

public class Atbash {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to encrypt or decrypt: ");
        String inputText = scanner.nextLine();

        String encryptedText = encrypt(inputText);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    public static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char encryptedChar = (char) (base + ('Z' - character));
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(character); // Non-letter characters remain unchanged
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text) {
        // Same as encryption because Atbash is symmetric
        return encrypt(text);
    }
}
