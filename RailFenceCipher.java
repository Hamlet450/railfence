import java.util.Scanner;

public class RailFenceCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to encrypt: ");
        String inputText = scanner.nextLine();
        System.out.print("Enter number of rails: ");
        int numRails = scanner.nextInt();

        String encryptedText = encrypt(inputText, numRails);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, numRails);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    public static String encrypt(String text, int numRails) {
        if (numRails <= 1) {
            return text;  // No need to encrypt if we only have one rail
        }

        // Create a 2D array to represent the rails and initialize all positions to '\0'
        char[][] railMatrix = new char[numRails][text.length()];
        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < text.length(); j++) {
                railMatrix[i][j] = '\0';
            }
        }

        int direction = 1; // 1 for moving down, -1 for moving up
        int rail = 0; // Start from the first rail

        // Traverse the text and place characters in the rail matrix
        for (int col = 0; col < text.length(); col++) {
            railMatrix[rail][col] = text.charAt(col);

            // Change direction when reaching the top or bottom rail
            if (rail == 0) {
                direction = 1;
            } else if (rail == numRails - 1) {
                direction = -1;
            }

            // Move to the next rail
            rail += direction;
        }

        // Print the rails
        System.out.println("\nRails:");
        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < text.length(); j++) {
                System.out.print(railMatrix[i][j] == '\0' ? " " : railMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        // Concatenate all rails to get the encrypted text
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (railMatrix[i][j] != '\0') {
                    encryptedText.append(railMatrix[i][j]);
                }
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text, int numRails) {
        if (numRails <= 1) {
            return text;  // No need to decrypt if we only have one rail
        }

        // Create a 2D array to represent the rails
        char[][] railMatrix = new char[numRails][text.length()];

        // Determine the position of each character in the zigzag pattern
        int direction = 1; // 1 for moving down, -1 for moving up
        int rail = 0;
        for (int col = 0; col < text.length(); col++) {
            railMatrix[rail][col] = '*'; // Use '*' as a placeholder

            if (rail == 0) {
                direction = 1;
            } else if (rail == numRails - 1) {
                direction = -1;
            }

            rail += direction;
        }

        // Place the encrypted text characters in the rail matrix
        int index = 0;
        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (railMatrix[i][j] == '*' && index < text.length()) {
                    railMatrix[i][j] = text.charAt(index++);
                }
            }
        }

        // Read the characters in the zigzag order to form the decrypted text
        StringBuilder decryptedText = new StringBuilder();
        rail = 0;
        direction = 1;
        for (int col = 0; col < text.length(); col++) {
            decryptedText.append(railMatrix[rail][col]);

            if (rail == 0) {
                direction = 1;
            } else if (rail == numRails - 1) {
                direction = -1;
            }

            rail += direction;
        }

        return decryptedText.toString();
    }
}
