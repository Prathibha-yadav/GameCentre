import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Set up the main frame
        JFrame frame = new JFrame("Game Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // Create a title label
        JLabel titleLabel = new JLabel("Welcome to Game Centre", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 87, 34));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel to hold buttons with Grid Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons for each game with color and font styles
        JButton guessTheNumberButton = createStyledButton("Guess the Number");
        JButton ticTacToeButton = createStyledButton("Tic-Tac-Toe");
        JButton mazeSolverButton = createStyledButton("Maze Solver");
        JButton sortingPuzzleButton = createStyledButton("Sorting Puzzle");
        JButton memoryMatchingButton = createStyledButton("Memory Matching");
        JButton towerOfHanoiButton = createStyledButton("Tower of Hanoi");
        JButton hangmanButton = createStyledButton("Hangman");

        // Add buttons to the panel
        buttonPanel.add(guessTheNumberButton);
        buttonPanel.add(ticTacToeButton);
        buttonPanel.add(mazeSolverButton);
        buttonPanel.add(sortingPuzzleButton);
        buttonPanel.add(memoryMatchingButton);
        buttonPanel.add(towerOfHanoiButton);
        buttonPanel.add(hangmanButton);

        // Add button panel to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Set up button actions
        guessTheNumberButton.addActionListener(e -> GuessTheNumber.play());
        ticTacToeButton.addActionListener(e -> TicTacToe.play());
        mazeSolverButton.addActionListener(e -> MazeSolver.play());
        sortingPuzzleButton.addActionListener(e -> SortingPuzzle.play());
        memoryMatchingButton.addActionListener(e -> MemoryMatching.play());
        towerOfHanoiButton.addActionListener(e -> TowerOfHanoi.play());
        hangmanButton.addActionListener(e -> Hangman.play());

        // Set frame visibility
        frame.setVisible(true);
    }

    // Helper method to create a styled button
    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(63, 81, 181)); // Indigo color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }
}
