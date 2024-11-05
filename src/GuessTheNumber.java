import javax.swing.*;
import java.util.Random;

public class GuessTheNumber {
    public static void play() {
        Random random = new Random();
        int number = random.nextInt(20) + 1;
        int attempts = 0;

        String userInput;
        do {
            userInput = JOptionPane.showInputDialog("Guess the number between 1 and 20:");
            if (userInput == null) return; // Cancel button pressed
            int guess = Integer.parseInt(userInput);
            attempts++;

            if (guess < number) {
                JOptionPane.showMessageDialog(null, "Too low! Try again.");
            } else if (guess > number) {
                JOptionPane.showMessageDialog(null, "Too high! Try again.");
            } else {
                JOptionPane.showMessageDialog(null, "Correct! Attempts: " + attempts);
            }
        } while (true);
    }
}
