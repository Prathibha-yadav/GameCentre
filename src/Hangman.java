import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class Hangman {
    private static final int MAX_ATTEMPTS = 6;
    private static String[] words = {"java", "swing", "project", "code", "game"};
    private static String selectedWord;
    private static Set<Character> guessedLetters = new HashSet<>();
    private static int attemptsLeft = MAX_ATTEMPTS;
    
    public static void play() {
        // Select a random word from the list
        selectedWord = words[(int)(Math.random() * words.length)];
        guessedLetters.clear();
        attemptsLeft = MAX_ATTEMPTS;
        
        // Set up JFrame
        JFrame frame = new JFrame("Hangman Game");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Display area for guessed word
        JTextArea displayWordArea = new JTextArea(2, 20);
        displayWordArea.setEditable(false);
        displayWordArea.setFont(new Font("Monospaced", Font.BOLD, 24));
        displayWordArea.setText(getDisplayWord());
        frame.add(displayWordArea, BorderLayout.NORTH);
        
        // Display area for guesses and attempts left
        JTextArea infoArea = new JTextArea(5, 30);
        infoArea.setEditable(false);
        updateInfoArea(infoArea);
        frame.add(infoArea, BorderLayout.CENTER);

        // Input for guesses
        JPanel inputPanel = new JPanel();
        JLabel guessLabel = new JLabel("Enter a letter:");
        JTextField guessField = new JTextField(5);
        JButton guessButton = new JButton("Guess");
        
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Action listener for guess button
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = guessField.getText().toLowerCase();
                if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                    JOptionPane.showMessageDialog(frame, "Please enter a single letter.");
                    return;
                }
                
                char guess = input.charAt(0);
                if (guessedLetters.contains(guess)) {
                    JOptionPane.showMessageDialog(frame, "You've already guessed this letter!");
                } else {
                    guessedLetters.add(guess);
                    if (selectedWord.indexOf(guess) == -1) {
                        attemptsLeft--;
                    }
                    updateInfoArea(infoArea);
                    displayWordArea.setText(getDisplayWord());

                    if (isWordGuessed()) {
                        JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the word: " + selectedWord);
                        frame.dispose();
                    } else if (attemptsLeft == 0) {
                        JOptionPane.showMessageDialog(frame, "Game Over! The word was: " + selectedWord);
                        frame.dispose();
                    }
                }
                guessField.setText("");
            }
        });

        frame.setVisible(true);
    }
    
    private static String getDisplayWord() {
        StringBuilder displayWord = new StringBuilder();
        for (char letter : selectedWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                displayWord.append(letter + " ");
            } else {
                displayWord.append("_ ");
            }
        }
        return displayWord.toString().trim();
    }

    private static void updateInfoArea(JTextArea infoArea) {
        infoArea.setText("Attempts left: " + attemptsLeft + "\nGuessed letters: " + guessedLetters.toString());
    }

    private static boolean isWordGuessed() {
        for (char letter : selectedWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }
}
